package com.sample.android.tmdb.repository.bypage.search

import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.repository.bypage.ItemDataSourceFactory
import com.sample.android.tmdb.repository.bypage.PageKeyRepository
import com.sample.android.tmdb.usecase.SearchUseCase
import java.util.concurrent.Executor

class SearchMoviePageKeyRepository(private val useCase: SearchUseCase)
    : PageKeyRepository<Movie, ItemApi.MovieWrapper>() {

    override fun getSourceFactory(query: String, retryExecutor: Executor): ItemDataSourceFactory<Movie, ItemApi.MovieWrapper> =
            SearchMovieDataSourceFactory(useCase = useCase,
                    query = query,
                    retryExecutor = retryExecutor)
}