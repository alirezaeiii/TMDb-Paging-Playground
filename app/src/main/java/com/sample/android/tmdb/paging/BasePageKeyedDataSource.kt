package com.sample.android.tmdb.paging

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.sample.android.tmdb.R
import com.sample.android.tmdb.domain.ItemWrapper
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.util.DisposableManager
import com.sample.android.tmdb.util.NetworkException
import com.sample.android.tmdb.util.isNetworkAvailable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor

abstract class BasePageKeyedDataSource<T : TmdbItem>(
    private val retryExecutor: Executor,
    private val context: Context
) : PageKeyedDataSource<Int, T>() {

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

    protected abstract fun fetchItems(page: Int): Observable<ItemWrapper<T>>

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        // ignored, since we only ever append to our initial load
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        _networkState.postValue(NetworkState.LOADING)
        fetchObservableItems(params.key).subscribe({
            _networkState.postValue(NetworkState.LOADED)
            retry = null
            callback.onResult(it.items, params.key + 1)
        }) {
            retry = {
                loadAfter(params, callback)
            }
            setErrorMsg(_networkState, it)
        }.also { DisposableManager.add(it) }
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, T>
    ) {
        _networkState.postValue(NetworkState.LOADING)
        _initialLoad.postValue(NetworkState.LOADING)
        fetchObservableItems(1).subscribe({
            _networkState.postValue(NetworkState.LOADED)
            _initialLoad.postValue(NetworkState.LOADED)
            callback.onResult(it.items, null, 2)
        }) {
            retry = {
                loadInitial(params, callback)
            }
            setErrorMsg(_networkState, it)
            setErrorMsg(_initialLoad, it)
        }.also { DisposableManager.add(it) }
    }

    private fun fetchObservableItems(page: Int): Observable<ItemWrapper<T>> =
        Observable.fromCallable { context.isNetworkAvailable() }.flatMap {
            return@flatMap if (it) composeObservable { fetchItems(page) }
            else Observable.error(NetworkException())
        }

    private fun setErrorMsg(networkState: MutableLiveData<NetworkState>, throwable: Throwable) {
        networkState.postValue(
            NetworkState.error(
                context.getString(
                    if (throwable is NetworkException) R.string.failed_network_msg
                    else R.string.failed_loading_msg
                )
            )
        )
    }

    private inline fun <T> composeObservable(task: () -> Observable<T>): Observable<T> = task()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}