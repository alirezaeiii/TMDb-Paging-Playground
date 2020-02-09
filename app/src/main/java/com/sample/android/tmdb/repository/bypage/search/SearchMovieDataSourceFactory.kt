package com.sample.android.tmdb.repository.bypage.search

import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import com.sample.android.tmdb.usecase.UseCase
import java.util.concurrent.Executor

class SearchMovieDataSourceFactory(
        private val useCase: UseCase,
        private val query: String,
        private val retryExecutor: Executor)
    : ItemDataSourceFactory<Movie, ItemApi.MovieWrapper>() {

    override fun getDataSource(): PageKeyedItemDataSource<Movie, ItemApi.MovieWrapper> =
            PageKeyedSearchMovieDataSource(useCase = useCase,
                    query = query,
                    retryExecutor = retryExecutor)
}