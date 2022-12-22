package com.sample.android.tmdb.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.sample.android.tmdb.domain.model.TmdbItem

abstract class BaseDataSourceFactory<T : TmdbItem> : DataSource.Factory<Int, T>() {

    private val _sourceLiveData = MutableLiveData<BasePageKeyedDataSource<T>>()
    val sourceLiveData: LiveData<BasePageKeyedDataSource<T>>
        get() = _sourceLiveData

    protected abstract val dataSource: BasePageKeyedDataSource<T>

    override fun create(): DataSource<Int, T> {
        val dataSource = this.dataSource
        _sourceLiveData.postValue(dataSource)
        return dataSource
    }
}