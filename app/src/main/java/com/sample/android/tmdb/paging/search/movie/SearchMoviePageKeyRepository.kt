package com.sample.android.tmdb.paging.search.movie

import android.content.Context
import com.sample.android.tmdb.domain.model.Movie
import com.sample.android.tmdb.data.network.MovieService
import com.sample.android.tmdb.paging.BaseDataSourceFactory
import com.sample.android.tmdb.paging.BasePageKeyRepository
import java.util.concurrent.Executor

class SearchMoviePageKeyRepository(
    api: MovieService,
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