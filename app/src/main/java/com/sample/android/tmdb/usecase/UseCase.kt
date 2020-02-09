package com.sample.android.tmdb.usecase

import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.domain.Cast
import com.sample.android.tmdb.domain.Person
import com.sample.android.tmdb.domain.Video
import com.sample.android.tmdb.util.SortType
import io.reactivex.Observable
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UseCase @Inject constructor(
        private val itemApi: ItemApi) : BaseUseCase() {

    fun fetchMovies(sortType: SortType, page: Int): Call<ItemApi.MovieWrapper> = when (sortType) {
        SortType.MOST_POPULAR -> itemApi.popularMovies(page)
        SortType.HIGHEST_RATED -> itemApi.highestRatedMovies(page)
        SortType.UPCOMING -> itemApi.upcomingMovies(page)
    }

    fun fetchTVShows(sortType: SortType, page: Int): Call<ItemApi.TVShowWrapper> = when (sortType) {
        SortType.MOST_POPULAR -> itemApi.popularTVShows(page)
        SortType.HIGHEST_RATED -> itemApi.topRatedTVShows(page)
        SortType.UPCOMING -> itemApi.latestTvShows(page)
    }

    fun fetchMovies(page: Int, query: String): Call<ItemApi.MovieWrapper> = itemApi.searchMovies(page, query)

    fun fetchTVShows(page: Int, query: String): Call<ItemApi.TVShowWrapper> = itemApi.searchTVShows(page, query)

    fun getMovieTrailers(id: Int): Observable<List<Video>> = composeObservable { itemApi.movieTrailers(id).map { it.videos } }

    fun getMovieCast(id: Int): Observable<List<Cast>> = composeObservable { itemApi.movieCast(id).map { it.cast } }

    fun getTvTrailers(id: Int): Observable<List<Video>> = composeObservable { itemApi.tvTrailers(id).map { it.videos } }

    fun getTvCast(id: Int): Observable<List<Cast>> = composeObservable { itemApi.tvCast(id).map { it.cast } }

    fun getPerson(id: Int): Observable<Person> = composeObservable { itemApi.person(id) }
}