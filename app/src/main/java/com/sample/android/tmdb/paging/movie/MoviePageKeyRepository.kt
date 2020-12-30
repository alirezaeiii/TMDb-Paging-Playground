package com.sample.android.tmdb.paging.movie

import android.content.Context
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.paging.TmdbDataSourceFactory
import com.sample.android.tmdb.paging.PageKeyRepository
import com.sample.android.tmdb.ui.item.SortType
import java.util.concurrent.Executor

class MoviePageKeyRepository(
        private val api: MovieApi,
        private val sortType: SortType,
        private val context: Context)
    : PageKeyRepository<Movie>() {

    override fun getSourceFactory(retryExecutor: Executor): TmdbDataSourceFactory<Movie> =
            MoviesDataSourceFactory(api = api,
                    sortType = sortType,
                    retryExecutor = retryExecutor,
                    context = context)
}