package com.sample.android.tmdb.paging.search.movie

import android.content.Context
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.paging.PageKeyedItemDataSource
import com.sample.android.tmdb.paging.TmdbDataSourceFactory
import java.util.concurrent.Executor

class SearchMovieDataSourceFactory(
        api: MovieApi,
        query: String,
        retryExecutor: Executor,
        context: Context
) : TmdbDataSourceFactory<Movie>() {

    override val dataSource: PageKeyedItemDataSource<Movie> by lazy {
        PageKeyedSearchMovieDataSource(api = api,
                query = query,
                retryExecutor = retryExecutor,
                context = context)
    }
}