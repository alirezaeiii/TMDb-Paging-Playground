package com.sample.android.tmdb.repository

import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.api.MovieApi
import com.sample.android.tmdb.api.MovieApi.MovieWrapper
import com.sample.android.tmdb.vo.Video
import io.reactivex.Observable
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRemoteDataSource @Inject
constructor(private val movieApi: MovieApi) {

    fun fetchMovies(sortType: SortType, page: Int): Call<MovieWrapper> {
        return when (sortType) {
            SortType.MOST_POPULAR -> movieApi.popularMovies(page)
            SortType.HIGHEST_RATED -> movieApi.highestRatedMovies(page)
            SortType.UPCOMING -> movieApi.upcomingMovies(page)
        }
    }

    fun fetchMovies(page : Int, query: String): Call<MovieWrapper> {
        return movieApi.searchMovies(page, query)
    }

    fun getTrailers(id: String): Observable<List<Video>> {
        return movieApi.trailers(id).map { it.videos }
    }
}