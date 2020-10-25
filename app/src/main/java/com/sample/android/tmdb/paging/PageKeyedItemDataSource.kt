package com.sample.android.tmdb.paging

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.sample.android.tmdb.R
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.domain.ItemWrapper
import com.sample.android.tmdb.util.EspressoIdlingResource
import com.sample.android.tmdb.util.isNetworkAvailable
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.Executor

abstract class PageKeyedItemDataSource<T : TmdbItem>(
        private val retryExecutor: Executor,
        private val context: Context)
    : PageKeyedDataSource<Int, T>() {

    // keep a function reference for the retry event
    private var retry: (() -> Any)? = null

    /**
     * There is no sync on the state because paging will always call loadInitial first then wait
     * for it to return some success value before calling loadAfter.
     */
    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _initialLoad = MutableLiveData<NetworkState>()
    val initialLoad: LiveData<NetworkState>
        get() = _initialLoad

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            retryExecutor.execute {
                it.invoke()
            }
        }
    }

    protected abstract fun fetchItems(page: Int): Call<ItemWrapper<T>>

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        // ignored, since we only ever append to our initial load
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        _networkState.postValue(NetworkState.LOADING)
        if (context.isNetworkAvailable()) {
            fetchItems(params.key).enqueue(object : retrofit2.Callback<ItemWrapper<T>> {

                override fun onFailure(call: Call<ItemWrapper<T>>, t: Throwable) {
                    // keep a lambda for future retry
                    retry = {
                        loadAfter(params, callback)
                    }
                    _networkState.postValue(NetworkState.error(context.getString(R.string.failed_loading_msg)))
                }

                override fun onResponse(call: Call<ItemWrapper<T>>, response: Response<ItemWrapper<T>>) {
                    if (response.isSuccessful) {
                        // clear retry since last request succeeded
                        retry = null
                        callback.onResult(response.body()?.items ?: emptyList(), params.key + 1)
                        _networkState.postValue(NetworkState.LOADED)
                    } else {
                        retry = {
                            loadAfter(params, callback)
                        }
                        _networkState.postValue(NetworkState.error(context.getString(R.string.failed_loading_msg)))
                    }
                }
            })
        } else {
            retry = {
                loadAfter(params, callback)
            }
            _networkState.postValue(NetworkState.error(context.getString(R.string.failed_network_msg)))
        }
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, T>) {
        _networkState.postValue(NetworkState.LOADING)
        _initialLoad.postValue(NetworkState.LOADING)

        if (context.isNetworkAvailable()) {
            // The network request might be handled in a different thread so make sure Espresso knows
            // that the app is busy until the response is handled.
            EspressoIdlingResource.increment() // App is busy until further notice

            // triggered by a refresh, we better execute sync
            try {
                val response = fetchItems(1).execute()
                if (response.isSuccessful) {
                    retry = null
                    _networkState.postValue(NetworkState.LOADED)
                    _initialLoad.postValue(NetworkState.LOADED)
                    callback.onResult(response.body()?.items ?: emptyList(), null, 2)
                } else {
                    retry = {
                        loadInitial(params, callback)
                    }
                    val error = NetworkState.error(context.getString(R.string.failed_loading_msg))
                    _networkState.postValue(error)
                    _initialLoad.postValue(error)
                }
            } catch (ioException: IOException) {
                retry = {
                    loadInitial(params, callback)
                }
                val error = NetworkState.error(context.getString(R.string.failed_loading_msg))
                _networkState.postValue(error)
                _initialLoad.postValue(error)

            } finally {
                if (!EspressoIdlingResource.getIdlingResource().isIdleNow) {
                    EspressoIdlingResource.decrement() // Set app as idle.
                }
            }
        } else {
            retry = {
                loadInitial(params, callback)
            }
            val error = NetworkState.error(context.getString(R.string.failed_network_msg))
            _networkState.postValue(error)
            _initialLoad.postValue(error)
        }
    }
}