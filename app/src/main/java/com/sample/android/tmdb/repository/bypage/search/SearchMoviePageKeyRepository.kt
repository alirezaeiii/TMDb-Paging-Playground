package com.sample.android.tmdb.repository.bypage.search

import android.content.Context
import com.sample.android.tmdb.network.TmdbApi
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyRepository
import java.util.concurrent.Executor

class SearchMoviePageKeyRepository(private val api: TmdbApi,
                                   private val context: Context)
    : PageKeyRepository<Movie, TmdbApi.MovieWrapper>() {

    override fun getSourceFactory(query: String, retryExecutor: Executor): ItemDataSourceFactory<Movie, TmdbApi.MovieWrapper> =
            SearchMovieDataSourceFactory(api = api,
                    query = query,
                    retryExecutor = retryExecutor,
                    context = context)
}