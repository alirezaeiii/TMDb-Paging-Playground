package com.sample.android.tmdb.paging.search.movie

import android.content.Context
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.paging.BaseDataSourceFactory
import com.sample.android.tmdb.paging.BasePageKeyRepository
import java.util.concurrent.Executor

class SearchMoviePageKeyRepository(private val api: MovieApi,
                                   private val query : String,
                                   private val retryExecutor : Executor,
                                   private val context: Context)
    : BasePageKeyRepository<Movie>(retryExecutor) {

    override fun getSourceFactory(): BaseDataSourceFactory<Movie> =
            SearchMovieDataSourceFactory(api = api,
                    query = query,
                    retryExecutor = retryExecutor,
                    context = context)
}