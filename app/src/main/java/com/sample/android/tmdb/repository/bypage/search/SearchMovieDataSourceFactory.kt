package com.sample.android.tmdb.repository.bypage.search

import android.content.Context
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import java.util.concurrent.Executor

class SearchMovieDataSourceFactory(
        private val api: MovieApi,
        private val query: String,
        private val retryExecutor: Executor,
        private val context: Context)
    : ItemDataSourceFactory<Movie>() {

    override fun getDataSource(): PageKeyedItemDataSource<Movie> =
            PageKeyedSearchMovieDataSource(api = api,
                    query = query,
                    retryExecutor = retryExecutor,
                    context = context)
}