package com.sample.android.tmdb.repository

import com.sample.android.tmdb.network.TVShowApi
import javax.inject.Inject

class TVShowDetailRepositoryImpl @Inject constructor(
    private val tvShowApi: TVShowApi
) : TVShowDetailRepository {

    override fun getTVShowTrailers(tvId: Int) = tvShowApi.tvTrailers(tvId)

    override fun getTVShowCredit(tvId: Int) = tvShowApi.tvCredit(tvId)
}