package com.sample.android.tmdb.repository.bypage.tvshow

import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.network.TmdbApi
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import com.sample.android.tmdb.util.SortType
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.Executor

class PageKeyedTVShowsDataSource(
        private val api: TmdbApi,
        private val sortType: SortType,
        retryExecutor: Executor)
    : PageKeyedItemDataSource<TVShow, TmdbApi.TVShowWrapper>(retryExecutor) {

    override fun getItems(response: Response<TmdbApi.TVShowWrapper>): List<TVShow> =
            response.body()?.tvShows ?: emptyList()


    override fun fetchItems(page: Int): Call<TmdbApi.TVShowWrapper> = when (sortType) {
        SortType.MOST_POPULAR -> api.popularTVShows(page)
        SortType.HIGHEST_RATED -> api.topRatedTVShows(page)
        SortType.UPCOMING -> api.latestTvShows(page)
    }
}