package com.sample.android.tmdb.repository

import com.sample.android.tmdb.domain.CreditWrapper
import com.sample.android.tmdb.domain.VideoWrapper
import io.reactivex.Single

interface TVShowDetailRepository {

    fun getTVShowTrailers(tvId: Int): Single<VideoWrapper>

    fun getTVShowCredit(tvId: Int): Single<CreditWrapper>
}