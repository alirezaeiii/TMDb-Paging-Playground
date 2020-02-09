package com.sample.android.tmdb.repository.bypage.search

import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import com.sample.android.tmdb.usecase.UseCase
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.Executor

class PageKeyedSearchMovieDataSource(
        private val useCase: UseCase,
        private val query: String,
        retryExecutor: Executor)
    : PageKeyedItemDataSource<Movie, ItemApi.MovieWrapper>(retryExecutor) {

    override fun getItems(response: Response<ItemApi.MovieWrapper>): List<Movie> =
            response.body()?.movies?.map { it } ?: emptyList()

    override fun fetchItems(page: Int): Call<ItemApi.MovieWrapper> =
            useCase.fetchMovies(page = page, query = query)
}