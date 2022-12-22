package com.sample.android.tmdb.paging.tvshow

import android.content.Context
import com.sample.android.tmdb.data.asTVShowDomainModel
import com.sample.android.tmdb.domain.model.TVShow
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.paging.BasePageKeyedDataSource
import com.sample.android.tmdb.ui.paging.main.SortType
import io.reactivex.Observable
import java.util.concurrent.Executor

class TVShowsPageKeyedDataSource(
    private val api: TVShowApi,
    private val sortType: SortType,
    retryExecutor: Executor,
    context: Context)
    : BasePageKeyedDataSource<TVShow>(retryExecutor, context) {

    override fun fetchItems(page: Int): Observable<List<TVShow>> = when (sortType) {
        SortType.MOST_POPULAR -> api.popularTVSeries(page).asTVShowDomainModel()
        SortType.HIGHEST_RATED -> api.topRatedTVSeries(page).asTVShowDomainModel()
        SortType.UPCOMING -> api.onTheAirTVSeries(page).asTVShowDomainModel()
        SortType.TRENDING -> api.trendingTVSeries(page).asTVShowDomainModel()
        SortType.NOW_PLAYING -> api.airingTodayTVSeries(page).asTVShowDomainModel()
    }
}