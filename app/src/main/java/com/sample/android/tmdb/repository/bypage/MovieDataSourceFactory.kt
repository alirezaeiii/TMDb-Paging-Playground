package com.sample.android.tmdb.repository.bypage

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.vo.Movie

class MovieDataSourceFactory(
        private val dataSource: MoviesRemoteDataSource,
        private val sortType: SortType?,
        private val query : String) : DataSource.Factory<String, Movie>() {
    val sourceLiveData = MutableLiveData<PageKeyedMovieDataSource>()
    override fun create(): DataSource<String, Movie> {
        val source = PageKeyedMovieDataSource(dataSource = dataSource,
                sortType = sortType,
                query = query)
        sourceLiveData.postValue(source)
        return source
    }
}