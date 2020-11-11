package com.sample.android.tmdb.paging.search.movie

import android.content.Context
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.paging.TmdbDataSourceFactory
import com.sample.android.tmdb.paging.PageKeyRepository
import com.sample.android.tmdb.paging.search.movie.SearchMovieDataSourceFactory
import java.util.concurrent.Executor

class SearchMoviePageKeyRepository(private val api: MovieApi,
                                   private val query : String,
                                   private val context: Context)
    : PageKeyRepository<Movie>() {

    override fun getSourceFactory(retryExecutor: Executor): TmdbDataSourceFactory<Movie> =
            SearchMovieDataSourceFactory(api = api,
                    query = query,
                    retryExecutor = retryExecutor,
                    context = context)
}