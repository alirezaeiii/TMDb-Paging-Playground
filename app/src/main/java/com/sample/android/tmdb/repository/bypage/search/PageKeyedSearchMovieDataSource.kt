package com.sample.android.tmdb.repository.bypage.search

import com.sample.android.tmdb.network.TmdbApi
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.Executor

class PageKeyedSearchMovieDataSource(
        private val api: TmdbApi,
        private val query: String,
        retryExecutor: Executor)
    : PageKeyedItemDataSource<Movie, TmdbApi.MovieWrapper>(retryExecutor) {

    override fun getItems(response: Response<TmdbApi.MovieWrapper>): List<Movie> =
            response.body()?.movies?.map { it } ?: emptyList()

    override fun fetchItems(page: Int): Call<TmdbApi.MovieWrapper> =
            api.searchMovies(page, query)
}