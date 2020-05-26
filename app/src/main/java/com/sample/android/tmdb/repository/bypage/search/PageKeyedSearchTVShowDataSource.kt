package com.sample.android.tmdb.repository.bypage.search

import android.content.Context
import com.sample.android.tmdb.network.TmdbApi
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.Executor

class PageKeyedSearchTVShowDataSource(
        private val api: TmdbApi,
        private val query: String,
        retryExecutor: Executor,
        context: Context)
    : PageKeyedItemDataSource<TVShow, TmdbApi.TVShowWrapper>(retryExecutor, context) {

    override fun getItems(response: Response<TmdbApi.TVShowWrapper>): List<TVShow> =
            response.body()?.tvShows ?: emptyList()


    override fun fetchItems(page: Int): Call<TmdbApi.TVShowWrapper> =
            api.searchTVShows(page, query)
}