package com.sample.android.tmdb.repository

import com.sample.android.tmdb.data.network.TVShowService
import com.sample.android.tmdb.data.response.asCreditWrapper
import com.sample.android.tmdb.data.response.asDomainModel
import com.sample.android.tmdb.domain.repository.TVShowDetailRepository
import com.sample.android.tmdb.domain.model.CreditWrapper
import com.sample.android.tmdb.domain.model.Video
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TVShowDetailRepositoryImpl @Inject constructor(
    private val tvShowApi: TVShowService
) : TVShowDetailRepository {

    override fun getTVShowTrailers(tvId: Int): Single<List<Video>> =
        tvShowApi.tvTrailers(tvId).map { it.videos.asDomainModel() }

    override fun getTVShowCredit(tvId: Int): CreditWrapper =
        tvShowApi.tvCredit(tvId).asCreditWrapper()
}