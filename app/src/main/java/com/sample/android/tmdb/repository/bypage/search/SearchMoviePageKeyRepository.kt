package com.sample.android.tmdb.repository.bypage.search

import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.repository.RemoteDataSource
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyRepository
import java.util.concurrent.Executor

class SearchMoviePageKeyRepository(private val dataSource: RemoteDataSource,
                                   private val networkExecutor: Executor)
    : PageKeyRepository<Movie, ItemApi.MovieWrapper>(networkExecutor) {

    override fun getSourceFactory(query: String): ItemDataSourceFactory<Movie, ItemApi.MovieWrapper> =
            SearchMovieDataSourceFactory(dataSource = dataSource,
                    query = query,
                    retryExecutor = networkExecutor)
}