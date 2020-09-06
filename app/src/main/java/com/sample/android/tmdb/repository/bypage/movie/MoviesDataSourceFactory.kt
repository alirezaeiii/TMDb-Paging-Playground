package com.sample.android.tmdb.repository.bypage.movie

import android.content.Context
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import com.sample.android.tmdb.util.SortType
import java.util.concurrent.Executor

class MoviesDataSourceFactory(
        private val api: MovieApi,
        private val sortType: SortType,
        private val retryExecutor: Executor,
        private val context: Context)
    : ItemDataSourceFactory<Movie>() {

    override fun getDataSource(): PageKeyedItemDataSource<Movie> =
            PageKeyedMovieDataSource(api = api,
                    sortType = sortType,
                    retryExecutor = retryExecutor,
                    context = context)
}
