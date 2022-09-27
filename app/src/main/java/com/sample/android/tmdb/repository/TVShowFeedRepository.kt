package com.sample.android.tmdb.repository

import android.content.Context
import com.sample.android.tmdb.R
import com.sample.android.tmdb.di.IoDispatcher
import com.sample.android.tmdb.domain.ItemWrapper
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.domain.repo.BaseFeedRepository
import com.sample.android.tmdb.network.TVShowApi
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TVShowFeedRepository @Inject constructor(
    context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val tvShowApi: TVShowApi,
) : BaseFeedRepository<TVShow>(context, ioDispatcher) {

    override suspend fun popularItems(): ItemWrapper<TVShow> = tvShowApi.popularTVSeries()

    override suspend fun latestItems(): ItemWrapper<TVShow> = tvShowApi.onTheAirTVSeries()

    override suspend fun topRatedItems(): ItemWrapper<TVShow> = tvShowApi.topRatedTVSeries()

    override suspend fun trendingItems(): ItemWrapper<TVShow> = tvShowApi.trendingTVSeries()

    override suspend fun nowPlayingItems(): ItemWrapper<TVShow> = tvShowApi.airingTodayTVSeries()

    override fun getNowPlayingResId(): Int = R.string.text_airing_today

    override fun getLatestResId(): Int = R.string.text_on_the_air
}