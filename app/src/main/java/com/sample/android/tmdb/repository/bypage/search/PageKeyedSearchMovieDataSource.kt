package com.sample.android.tmdb.repository.bypage.search

import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.repository.RemoteDataSource
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.Executor

class PageKeyedSearchMovieDataSource(
        private val dataSource: RemoteDataSource,
        private val query: String,
        retryExecutor: Executor)
    : PageKeyedItemDataSource<Movie, ItemApi.MovieWrapper>(retryExecutor) {

    override fun getItems(response: Response<ItemApi.MovieWrapper>): List<Movie> =
            response.body()?.movies?.map { it } ?: emptyList()

    override fun fetchItems(page: Int): Call<ItemApi.MovieWrapper> =
            dataSource.fetchMovies(page = page, query = query)
}