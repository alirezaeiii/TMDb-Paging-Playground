package com.sample.android.tmdb.data.repository

import android.content.Context
import com.sample.android.tmdb.R
import com.sample.android.tmdb.data.network.TVShowService
import com.sample.android.tmdb.data.response.asTVShowDomainModel
import com.sample.android.tmdb.di.IoDispatcher
import com.sample.android.tmdb.domain.model.TVShow
import com.sample.android.tmdb.domain.repository.BaseFeedRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TVShowFeedRepository @Inject constructor(
    context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val tvShowApi: TVShowService,
) : BaseFeedRepository<TVShow>(context, ioDispatcher) {

    override suspend fun popularItems(): List<TVShow> = tvShowApi.popularTVSeries().items.asTVShowDomainModel()

    override suspend fun latestItems(): List<TVShow> = tvShowApi.onTheAirTVSeries().items.asTVShowDomainModel()

    override suspend fun topRatedItems(): List<TVShow> = tvShowApi.topRatedTVSeries().items.asTVShowDomainModel()

    override suspend fun trendingItems(): List<TVShow> = tvShowApi.trendingTVSeries().items.asTVShowDomainModel()

    override suspend fun nowPlayingItems(): List<TVShow> = tvShowApi.airingTodayTVSeries().items.asTVShowDomainModel()

    override suspend fun discoverItems(): List<TVShow> = tvShowApi.discoverTVSeries().items.asTVShowDomainModel()

    override fun getNowPlayingResId(): Int = R.string.text_airing_today

    override fun getLatestResId(): Int = R.string.text_on_the_air
}