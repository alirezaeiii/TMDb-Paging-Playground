package com.sample.android.tmdb.repository.bypage.movie

import android.content.Context
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.network.TmdbApi
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyRepository
import com.sample.android.tmdb.util.SortType
import java.util.concurrent.Executor

class MoviePageKeyRepository(
        private val api: TmdbApi,
        private val sortType: SortType,
        private val context: Context)
    : PageKeyRepository<Movie, TmdbApi.MovieWrapper>() {

    override fun getSourceFactory(query: String, retryExecutor: Executor): ItemDataSourceFactory<Movie, TmdbApi.MovieWrapper> =
            MoviesDataSourceFactory(api = api,
                    sortType = sortType,
                    retryExecutor = retryExecutor,
                    context = context)
}