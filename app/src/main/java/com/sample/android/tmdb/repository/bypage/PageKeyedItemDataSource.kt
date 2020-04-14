package com.sample.android.tmdb.repository.bypage

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.repository.NetworkState
import com.sample.android.tmdb.util.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.Executor

abstract class PageKeyedItemDataSource<T : TmdbItem, E>(
        private val retryExecutor: Executor)
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

    protected abstract fun getItems(response: Response<E>): List<T>

    protected abstract fun fetchItems(page: Int): Call<E>

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        // ignored, since we only ever append to our initial load
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {

        _networkState.postValue(NetworkState.LOADING)
        fetchItems(params.key).enqueue(object : retrofit2.Callback<E> {

            override fun onFailure(call: Call<E>, t: Throwable) {
                // keep a lambda for future retry
                retry = {
                    loadAfter(params, callback)
                }
                _networkState.postValue(NetworkState.error(t.message ?: "unknown err"))
            }

            override fun onResponse(call: Call<E>, response: Response<E>) {
                if (response.isSuccessful) {
                    // clear retry since last request succeeded
                    retry = null
                    callback.onResult(getItems(response), params.key + 1)
                    _networkState.postValue(NetworkState.LOADED)
                } else {
                    retry = {
                        loadAfter(params, callback)
                    }
                    _networkState.postValue(
                            NetworkState.error("error code: ${response.code()} " +
                                    response.message()))
                }
            }
        }
        )
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, T>) {
        _networkState.postValue(NetworkState.LOADING)
        _initialLoad.postValue(NetworkState.LOADING)

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
                callback.onResult(getItems(response), null, 2)
            } else {
                retry = {
                    loadInitial(params, callback)
                }
                val error = NetworkState.error("error code: ${response.code()} " + response.message())
                _networkState.postValue(error)
                _initialLoad.postValue(error)
            }
        } catch (ioException: IOException) {
            retry = {
                loadInitial(params, callback)
            }
            val error = NetworkState.error(ioException.message ?: "unknown error")
            _networkState.postValue(error)
            _initialLoad.postValue(error)
        } finally {
            if (!EspressoIdlingResource.getIdlingResource().isIdleNow) {
                EspressoIdlingResource.decrement() // Set app as idle.
            }
        }
    }
}