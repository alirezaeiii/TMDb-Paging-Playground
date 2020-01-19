package com.sample.android.tmdb.repository.bypage

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.sample.android.tmdb.domain.TmdbItem

abstract class ItemDataSourceFactory<T : TmdbItem, E> : DataSource.Factory<Int, T>() {

    private val _sourceLiveData = MutableLiveData<PageKeyedItemDataSource<T, E>>()
    val sourceLiveData: LiveData<PageKeyedItemDataSource<T, E>>
        get() = _sourceLiveData

    protected abstract fun getDataSource(): PageKeyedItemDataSource<T, E>

    override fun create(): DataSource<Int, T> {
        val source = getDataSource()
        _sourceLiveData.postValue(source)
        return source
    }
}