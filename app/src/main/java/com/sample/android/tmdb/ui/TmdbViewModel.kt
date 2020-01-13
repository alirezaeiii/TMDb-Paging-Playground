package com.sample.android.tmdb.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations.switchMap
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.repository.Listing
import com.sample.android.tmdb.repository.NetworkState
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

abstract class TmdbViewModel<T : TmdbItem>(
        // thread pool used for network requests
        protected val NETWORK_IO: ExecutorService = Executors.newFixedThreadPool(5))
    : ViewModel() {

    protected val query = MutableLiveData<String>()
    protected abstract val repoResult: LiveData<Listing<T>>

    val items: LiveData<PagedList<T>> by lazy { switchMap(repoResult) { it.pagedList } }
    val networkState: LiveData<NetworkState> by lazy { switchMap(repoResult) { it.networkState } }
    val refreshState: LiveData<NetworkState> by lazy { switchMap(repoResult) { it.refreshState } }

    fun refresh() {
        repoResult.value?.refresh?.invoke()
    }

    fun showQuery(query: String): Boolean {
        if (this.query.value == query) {
            return false
        }
        this.query.value = query
        return true
    }

    fun retry() {
        repoResult.value?.retry?.invoke()
    }
}