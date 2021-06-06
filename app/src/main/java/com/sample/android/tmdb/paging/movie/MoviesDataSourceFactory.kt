package com.sample.android.tmdb.paging.movie

import android.content.Context
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.paging.BaseDataSourceFactory
import com.sample.android.tmdb.paging.BasePageKeyedDataSource
import com.sample.android.tmdb.ui.item.SortType
import java.util.concurrent.Executor

class MoviesDataSourceFactory(
    private val api: MovieApi,
    private val sortType: SortType,
    private val retryExecutor: Executor,
    private val context: Context
) : BaseDataSourceFactory<Movie>() {

    override fun getDataSource(): BasePageKeyedDataSource<Movie> =
        MoviePageKeyedDataSource(
            api = api,
            sortType = sortType,
            retryExecutor = retryExecutor,
            context = context
        )
}
