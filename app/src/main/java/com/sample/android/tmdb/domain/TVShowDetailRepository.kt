package com.sample.android.tmdb.domain

import com.sample.android.tmdb.data.CreditWrapper
import com.sample.android.tmdb.data.VideoWrapper
import io.reactivex.Single

interface TVShowDetailRepository {

    fun getTVShowTrailers(tvId: Int): Single<VideoWrapper>

    fun getTVShowCredit(tvId: Int): Single<CreditWrapper>
}