package com.sample.android.tmdb.usecase

import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.util.SortType
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemUseCase @Inject constructor(
        private val itemApi: ItemApi) {

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
}