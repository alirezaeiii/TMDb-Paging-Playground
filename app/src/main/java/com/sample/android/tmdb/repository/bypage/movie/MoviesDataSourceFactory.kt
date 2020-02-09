package com.sample.android.tmdb.repository.bypage.movie

import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import com.sample.android.tmdb.usecase.UseCase
import com.sample.android.tmdb.util.SortType
import java.util.concurrent.Executor

class MoviesDataSourceFactory(
        private val useCase: UseCase,
        private val sortType: SortType,
        private val retryExecutor: Executor)
    : ItemDataSourceFactory<Movie, ItemApi.MovieWrapper>() {

    override fun getDataSource(): PageKeyedItemDataSource<Movie, ItemApi.MovieWrapper> =
            PageKeyedMovieDataSource(useCase = useCase,
                    sortType = sortType,
                    retryExecutor = retryExecutor)
}
