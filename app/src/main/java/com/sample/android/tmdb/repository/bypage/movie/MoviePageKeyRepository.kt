package com.sample.android.tmdb.repository.bypage.movie

import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyRepository
import com.sample.android.tmdb.usecase.ItemUseCase
import com.sample.android.tmdb.util.SortType
import java.util.concurrent.Executor

class MoviePageKeyRepository(
        private val useCase: ItemUseCase,
        private val sortType: SortType)
    : PageKeyRepository<Movie, ItemApi.MovieWrapper>() {

    override fun getSourceFactory(query: String, retryExecutor: Executor): ItemDataSourceFactory<Movie, ItemApi.MovieWrapper> =
            MoviesDataSourceFactory(useCase = useCase,
                    sortType = sortType,
                    retryExecutor = retryExecutor)
}