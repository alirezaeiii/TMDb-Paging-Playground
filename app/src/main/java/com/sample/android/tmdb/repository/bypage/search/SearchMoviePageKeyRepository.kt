package com.sample.android.tmdb.repository.bypage.search

import android.content.Context
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyRepository
import java.util.concurrent.Executor

class SearchMoviePageKeyRepository(private val api: MovieApi,
                                   private val context: Context)
    : PageKeyRepository<Movie>() {

    override fun getSourceFactory(query: String, retryExecutor: Executor): ItemDataSourceFactory<Movie> =
            SearchMovieDataSourceFactory(api = api,
                    query = query,
                    retryExecutor = retryExecutor,
                    context = context)
}