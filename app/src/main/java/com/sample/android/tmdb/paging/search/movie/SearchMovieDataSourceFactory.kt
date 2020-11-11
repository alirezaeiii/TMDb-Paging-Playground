package com.sample.android.tmdb.paging.search.movie

import android.content.Context
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.paging.TmdbDataSourceFactory
import com.sample.android.tmdb.paging.PageKeyedItemDataSource
import com.sample.android.tmdb.paging.search.movie.PageKeyedSearchMovieDataSource
import java.util.concurrent.Executor

class SearchMovieDataSourceFactory(
        private val api: MovieApi,
        private val query: String,
        private val retryExecutor: Executor,
        private val context: Context)
    : TmdbDataSourceFactory<Movie>() {

    override fun getDataSource(): PageKeyedItemDataSource<Movie> =
            PageKeyedSearchMovieDataSource(api = api,
                    query = query,
                    retryExecutor = retryExecutor,
                    context = context)
}