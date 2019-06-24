package com.sample.android.tmdb.repository.bypage

import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.vo.TVShow
import retrofit2.Call
import retrofit2.Response

class PageKeyedTVShowsDataSource(private val dataSource: MoviesRemoteDataSource,
                                 private val sortType: SortType?,
                                 private val query: String) : PageKeyedItemDataSource<TVShow, ItemApi.TVShowWrapper>() {

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