package com.sample.android.tmdb.repository.bypage.tvshow

import com.sample.android.tmdb.util.SortType
import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import com.sample.android.tmdb.domain.TVShow
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.Executor

class PageKeyedTVShowsDataSource(
        private val dataSource: MoviesRemoteDataSource,
        private val sortType: SortType?,
        private val query: String,
        retryExecutor: Executor)
    : PageKeyedItemDataSource<TVShow, ItemApi.TVShowWrapper>(retryExecutor) {

    override fun getItems(response: Response<ItemApi.TVShowWrapper>): List<TVShow> {
        val data = response.body()?.tvShows
        return data?.map { it } ?: emptyList()
    }

    override fun fetchItems(page: Int): Call<ItemApi.TVShowWrapper> {
        if (sortType != null) {
            return dataSource.fetchTVShows(sortType = sortType, page = page)
        } else if (query.isNotEmpty()) {
            return dataSource.fetchTVShows(page = page, query = query)
        }
        throw RuntimeException("Unknown state to fetch items")
    }
}