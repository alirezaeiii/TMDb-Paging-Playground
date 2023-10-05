package com.sample.android.tmdb.data.paging.search.movie

import android.content.Context
import com.sample.android.tmdb.domain.model.Movie
import com.sample.android.tmdb.data.network.MovieService
import com.sample.android.tmdb.data.paging.BasePageKeyedDataSource
import com.sample.android.tmdb.data.paging.BaseDataSourceFactory
import java.util.concurrent.Executor

class SearchMovieDataSourceFactory(
    private val api: MovieService,
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