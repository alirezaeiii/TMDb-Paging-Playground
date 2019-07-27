package com.sample.android.tmdb.repository.bypage.movie

import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import com.sample.android.tmdb.vo.Movie
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.Executor

class PageKeyedMovieDataSource(
        private val dataSource: MoviesRemoteDataSource,
        private val sortType: SortType?,
        private val query: String,
        retryExecutor: Executor)
    : PageKeyedItemDataSource<Movie, ItemApi.MovieWrapper>(retryExecutor) {

    override fun getItems(response: Response<ItemApi.MovieWrapper>): List<Movie> {
        val data = response.body()?.movies
        return data?.map { it } ?: emptyList()
    }

    override fun fetchItems(page: Int): Call<ItemApi.MovieWrapper> {
        if (sortType != null) {
            return dataSource.fetchMovies(sortType = sortType, page = page)
        } else if (query.isNotEmpty()) {
            return dataSource.fetchMovies(page = page, query = query)
        }
        throw RuntimeException("Unknown state to fetch items")
    }
}