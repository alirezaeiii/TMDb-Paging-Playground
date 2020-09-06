package com.sample.android.tmdb.repository.bypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.sample.android.tmdb.domain.TmdbItem

abstract class ItemDataSourceFactory<T : TmdbItem> : DataSource.Factory<Int, T>() {

    private val _sourceLiveData = MutableLiveData<PageKeyedItemDataSource<T>>()
    val sourceLiveData: LiveData<PageKeyedItemDataSource<T>>
        get() = _sourceLiveData

    protected abstract fun getDataSource(): PageKeyedItemDataSource<T>

    override fun create(): DataSource<Int, T> {
        val source = getDataSource()
        _sourceLiveData.postValue(source)
        return source
    }
}