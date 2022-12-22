package com.sample.android.tmdb.paging.search.movie

import android.content.Context
import com.sample.android.tmdb.domain.model.Movie
import com.sample.android.tmdb.data.network.MovieApi
import com.sample.android.tmdb.paging.BasePageKeyedDataSource
import com.sample.android.tmdb.paging.BaseDataSourceFactory
import java.util.concurrent.Executor

class SearchMovieDataSourceFactory(
    private val api: MovieApi,
    private val query: String,
    private val retryExecutor: Executor,
    private val context: Context
) : BaseDataSourceFactory<Movie>() {

    override val dataSource: BasePageKeyedDataSource<Movie>
        get() = SearchMoviePageKeyedDataSource(
            api = api,
            query = query,
            retryExecutor = retryExecutor,
            context = context
        )
}