package com.sample.android.tmdb.ui.paging.search

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.sample.android.tmdb.domain.model.TmdbItem
import com.sample.android.tmdb.domain.paging.Listing
import com.sample.android.tmdb.domain.paging.BasePageKeyRepository
import com.sample.android.tmdb.ui.paging.BasePagingViewModel

abstract class BaseSearchViewModel<T : TmdbItem>(app: Application) : BasePagingViewModel<T>(app) {

    private val query = MutableLiveData<String>()

    protected abstract fun searchRepoResult(query : String) : BasePageKeyRepository<T>

    override val repoResult: LiveData<Listing<T>> = Transformations.map(query) {
        searchRepoResult(it).getItems()
    }

    fun showQuery(query: String): Boolean {
        if (this.query.value == query) {
            return false
        }
        this.query.value = query
        return true
    }
}