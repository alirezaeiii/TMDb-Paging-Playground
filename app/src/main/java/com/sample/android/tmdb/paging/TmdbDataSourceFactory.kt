package com.sample.android.tmdb.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.sample.android.tmdb.domain.TmdbItem

abstract class TmdbDataSourceFactory<T : TmdbItem> : DataSource.Factory<Int, T>() {

    private val _sourceLiveData = MutableLiveData<PageKeyedItemDataSource<T>>()
    val sourceLiveData: LiveData<PageKeyedItemDataSource<T>>
        get() = _sourceLiveData

    protected abstract val dataSource: PageKeyedItemDataSource<T>

    override fun create(): DataSource<Int, T> {
        _sourceLiveData.postValue(dataSource)
        return dataSource
    }
}