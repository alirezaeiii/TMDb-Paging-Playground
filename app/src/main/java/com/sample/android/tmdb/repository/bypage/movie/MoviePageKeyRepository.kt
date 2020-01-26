package com.sample.android.tmdb.repository.bypage.movie

import com.sample.android.tmdb.util.SortType
import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.repository.RemoteDataSource
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyRepository
import com.sample.android.tmdb.domain.Movie
import java.util.concurrent.Executor

class MoviePageKeyRepository(
        private val dataSource: RemoteDataSource,
        private val sortType: SortType)
    : PageKeyRepository<Movie, ItemApi.MovieWrapper>() {

    override fun getSourceFactory(query: String, retryExecutor: Executor): ItemDataSourceFactory<Movie, ItemApi.MovieWrapper> =
            MoviesDataSourceFactory(dataSource = dataSource,
                    sortType = sortType,
                    retryExecutor = retryExecutor)
}