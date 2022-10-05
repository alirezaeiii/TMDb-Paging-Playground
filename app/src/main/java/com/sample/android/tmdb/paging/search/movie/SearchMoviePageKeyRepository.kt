package com.sample.android.tmdb.paging.search.movie

import android.content.Context
import com.sample.android.tmdb.data.Movie
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.paging.BaseDataSourceFactory
import com.sample.android.tmdb.paging.BasePageKeyRepository
import java.util.concurrent.Executor

class SearchMoviePageKeyRepository(
    api: MovieApi,
    query: String,
    retryExecutor: Executor,
    context: Context
) : BasePageKeyRepository<Movie>(retryExecutor) {

    override val sourceFactory: BaseDataSourceFactory<Movie> =
        SearchMovieDataSourceFactory(
            api = api,
            query = query,
            retryExecutor = retryExecutor,
            context = context
        )
}