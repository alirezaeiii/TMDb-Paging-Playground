package com.sample.android.tmdb.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations.switchMap
import android.arch.lifecycle.ViewModel
import com.sample.android.tmdb.repository.Listing
import java.util.concurrent.Executors

abstract class ItemViewModel<T> : ViewModel() {

    // thread pool used for network requests
    @Suppress("PrivatePropertyName")
    protected val NETWORK_IO = Executors.newFixedThreadPool(5)

    protected val query = MutableLiveData<String>()
    protected abstract val repoResult: LiveData<Listing<T>>

    val items by lazy { switchMap(repoResult) { it.pagedList } }
    val networkState by lazy { switchMap(repoResult) { it.networkState } }
    val refreshState by lazy { switchMap(repoResult) { it.refreshState } }

    fun refresh() {
        repoResult.value?.refresh?.invoke()
    }

    fun showQuery(query: String?): Boolean {
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