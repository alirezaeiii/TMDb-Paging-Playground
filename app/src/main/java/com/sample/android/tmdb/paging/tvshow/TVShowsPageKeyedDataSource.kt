package com.sample.android.tmdb.paging.tvshow

import android.content.Context
import com.sample.android.tmdb.data.TVShow
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.paging.BasePageKeyedDataSource
import com.sample.android.tmdb.ui.paging.main.SortType
import java.util.concurrent.Executor

class TVShowsPageKeyedDataSource(
    private val api: TVShowApi,
    private val sortType: SortType,
    retryExecutor: Executor,
    context: Context)
    : BasePageKeyedDataSource<TVShow>(retryExecutor, context) {

    override fun fetchItems(page: Int) = when (sortType) {
        SortType.MOST_POPULAR -> api.popularTVSeries(page)
        SortType.HIGHEST_RATED -> api.topRatedTVSeries(page)
        SortType.UPCOMING -> api.onTheAirTVSeries(page)
        SortType.TRENDING -> api.trendingTVSeries(page)
        SortType.NOW_PLAYING -> api.airingTodayTVSeries(page)
    }
}