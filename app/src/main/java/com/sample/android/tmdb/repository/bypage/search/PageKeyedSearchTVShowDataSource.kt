package com.sample.android.tmdb.repository.bypage.search

import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.repository.RemoteDataSource
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.Executor

class PageKeyedSearchTVShowDataSource(
        private val dataSource: RemoteDataSource,
        private val query: String,
        retryExecutor: Executor)
    : PageKeyedItemDataSource<TVShow, ItemApi.TVShowWrapper>(retryExecutor) {

    override fun getItems(response: Response<ItemApi.TVShowWrapper>): List<TVShow> =
            response.body()?.tvShows?.map { it } ?: emptyList()


    override fun fetchItems(page: Int): Call<ItemApi.TVShowWrapper> =
            dataSource.fetchTVShows(page = page, query = query)
}