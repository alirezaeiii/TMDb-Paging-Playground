package com.sample.android.tmdb.repository.bypage.movie

import android.content.Context
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.network.TmdbApi
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import com.sample.android.tmdb.util.SortType
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.Executor

class PageKeyedMovieDataSource(
        private val api: TmdbApi,
        private val sortType: SortType,
        retryExecutor: Executor,
        context: Context)
    : PageKeyedItemDataSource<Movie, TmdbApi.MovieWrapper>(retryExecutor, context) {

    override fun getItems(response: Response<TmdbApi.MovieWrapper>): List<Movie> =
            response.body()?.movies ?: emptyList()

    override fun fetchItems(page: Int): Call<TmdbApi.MovieWrapper> = when (sortType) {
        SortType.MOST_POPULAR -> api.popularMovies(page)
        SortType.HIGHEST_RATED -> api.highestRatedMovies(page)
        SortType.UPCOMING -> api.upcomingMovies(page)
    }
}