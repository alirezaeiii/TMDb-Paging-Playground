package com.sample.android.tmdb.ui.search

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.ui.TmdbViewModel

abstract class BaseSearchViewModel<T : TmdbItem>(app: Application) : TmdbViewModel<T>(app = app) {

    private val _query = MutableLiveData<String>()
    protected val query: LiveData<String>
        get() = _query

    fun showQuery(query: String): Boolean {
        if (_query.value == query) {
            return false
        }
        _query.value = query
        return true
    }
}