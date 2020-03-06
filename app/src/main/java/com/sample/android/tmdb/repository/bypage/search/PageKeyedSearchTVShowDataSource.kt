package com.sample.android.tmdb.repository.bypage.search

import com.sample.android.tmdb.network.TmdbApi
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.Executor

class PageKeyedSearchTVShowDataSource(
        private val api: TmdbApi,
        private val query: String,
        retryExecutor: Executor)
    : PageKeyedItemDataSource<TVShow, TmdbApi.TVShowWrapper>(retryExecutor) {

    override fun getItems(response: Response<TmdbApi.TVShowWrapper>): List<TVShow> =
            response.body()?.tvShows?.map { it } ?: emptyList()


    override fun fetchItems(page: Int): Call<TmdbApi.TVShowWrapper> =
            api.searchTVShows(page, query)
}