package com.sample.android.tmdb.repository

import com.sample.android.tmdb.util.SortType
import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.api.ItemApi.MovieWrapper
import com.sample.android.tmdb.api.ItemApi.TVShowWrapper
import com.sample.android.tmdb.domain.Cast
import com.sample.android.tmdb.domain.Person
import com.sample.android.tmdb.domain.Video
import io.reactivex.Observable
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject
constructor(private val itemApi: ItemApi) {

    fun fetchMovies(sortType: SortType, page: Int): Call<MovieWrapper> = when (sortType) {
        SortType.MOST_POPULAR -> itemApi.popularMovies(page)
        SortType.HIGHEST_RATED -> itemApi.highestRatedMovies(page)
        SortType.UPCOMING -> itemApi.upcomingMovies(page)
    }

    fun fetchTVShows(sortType: SortType, page: Int): Call<TVShowWrapper> = when (sortType) {
        SortType.MOST_POPULAR -> itemApi.popularTVShows(page)
        SortType.HIGHEST_RATED -> itemApi.topRatedTVShows(page)
        SortType.UPCOMING -> itemApi.latestTvShows(page)
    }

    fun fetchMovies(page: Int, query: String): Call<MovieWrapper> = itemApi.searchMovies(page, query)

    fun fetchTVShows(page: Int, query: String): Call<TVShowWrapper> = itemApi.searchTVShows(page, query)

    fun getMovieTrailers(id: Int): Observable<List<Video>> = itemApi.movieTrailers(id).map { it.videos }

    fun getMovieCast(id: Int): Observable<List<Cast>> = itemApi.movieCast(id).map { it.cast }

    fun getTvTrailers(id: Int): Observable<List<Video>> = itemApi.tvTrailers(id).map { it.videos }

    fun getTvCast(id: Int): Observable<List<Cast>> = itemApi.tvCast(id).map { it.cast }

    fun getPerson(id: Int): Observable<Person> = itemApi.person(id)
}