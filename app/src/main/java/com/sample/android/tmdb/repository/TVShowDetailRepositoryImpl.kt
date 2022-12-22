package com.sample.android.tmdb.repository

import com.sample.android.tmdb.data.asCastDomainModel
import com.sample.android.tmdb.data.asCrewDomainModel
import com.sample.android.tmdb.domain.TVShowDetailRepository
import com.sample.android.tmdb.domain.model.CreditWrapper
import com.sample.android.tmdb.network.TVShowApi
import javax.inject.Inject

class TVShowDetailRepositoryImpl @Inject constructor(
    private val tvShowApi: TVShowApi
) : TVShowDetailRepository {

    override fun getTVShowTrailers(tvId: Int) = tvShowApi.tvTrailers(tvId)

    override fun getTVShowCredit(tvId: Int) : CreditWrapper {
        val networkCreditWrapper = tvShowApi.tvCredit(tvId)
        val cast = networkCreditWrapper.map { it.cast.asCastDomainModel() }
        val crew = networkCreditWrapper.map { it.crew.asCrewDomainModel() }
        return CreditWrapper(cast, crew)
    }
}