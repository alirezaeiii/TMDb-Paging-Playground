package com.sample.android.tmdb.domain

import com.sample.android.tmdb.data.response.VideoWrapper
import com.sample.android.tmdb.domain.model.CreditWrapper
import io.reactivex.Single

interface TVShowDetailRepository {

    fun getTVShowTrailers(tvId: Int): Single<VideoWrapper>

    fun getTVShowCredit(tvId: Int): CreditWrapper
}