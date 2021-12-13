package com.sample.android.tmdb.repository

import android.content.Context
import com.sample.android.tmdb.domain.ItemWrapper
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.network.TVShowApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TVShowRepository @Inject constructor(
    context: Context,
    private val tvShowApi: TVShowApi,
) : TmdbRepository<TVShow>(context) {

    override suspend fun popular(): ItemWrapper<TVShow> = tvShowApi.popularTVSeries()

    override suspend fun latest(): ItemWrapper<TVShow> = tvShowApi.latestTVSeries()

    override suspend fun topRated(): ItemWrapper<TVShow> = tvShowApi.topRatedTVSeries()
}