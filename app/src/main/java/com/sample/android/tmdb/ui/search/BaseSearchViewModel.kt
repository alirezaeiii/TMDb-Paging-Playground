package com.sample.android.tmdb.ui.search

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.paging.Listing
import com.sample.android.tmdb.paging.TmdbPageKeyRepository
import com.sample.android.tmdb.ui.TmdbViewModel

abstract class BaseSearchViewModel<T : TmdbItem>(app: Application) : TmdbViewModel<T>(app = app) {

    private val query = MutableLiveData<String>()

    protected abstract fun getRepoResult(query : String) : TmdbPageKeyRepository<T>

    override val repoResult: LiveData<Listing<T>> = Transformations.map(query) {
        getRepoResult(it).getItems(NETWORK_IO)
    }

    fun showQuery(query: String): Boolean {
        if (this.query.value == query) {
            return false
        }
        this.query.value = query
        return true
    }
}