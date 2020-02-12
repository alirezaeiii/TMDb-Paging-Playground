package com.sample.android.tmdb.usecase

import com.sample.android.tmdb.api.ItemApi
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchUseCase @Inject constructor(
        private val itemApi: ItemApi) {

    fun fetchMovies(page: Int, query: String): Call<ItemApi.MovieWrapper> = itemApi.searchMovies(page, query)

    fun fetchTVShows(page: Int, query: String): Call<ItemApi.TVShowWrapper> = itemApi.searchTVShows(page, query)
}