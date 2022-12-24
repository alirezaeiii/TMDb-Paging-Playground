package com.sample.android.tmdb.repository

import com.sample.android.tmdb.data.network.TVShowApi
import com.sample.android.tmdb.data.response.asCastDomainModel
import com.sample.android.tmdb.data.response.asCrewDomainModel
import com.sample.android.tmdb.data.response.asDomainModel
import com.sample.android.tmdb.domain.TVShowDetailRepository
import com.sample.android.tmdb.domain.model.CreditWrapper
import com.sample.android.tmdb.domain.model.Video
import io.reactivex.Single
import javax.inject.Inject

class TVShowDetailRepositoryImpl @Inject constructor(
    private val tvShowApi: TVShowApi
) : TVShowDetailRepository {

    override fun getTVShowTrailers(tvId: Int): Single<List<Video>> =
        tvShowApi.tvTrailers(tvId).map { it.videos.asDomainModel() }

    override fun getTVShowCredit(tvId: Int): CreditWrapper {
        val networkCreditWrapper = tvShowApi.tvCredit(tvId)
        val cast = networkCreditWrapper.map { it.cast.asCastDomainModel() }
        val crew = networkCreditWrapper.map { it.crew.asCrewDomainModel() }
        return CreditWrapper(cast, crew)
    }
}