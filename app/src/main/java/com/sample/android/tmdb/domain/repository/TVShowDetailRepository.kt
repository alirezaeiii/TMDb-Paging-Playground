package com.sample.android.tmdb.domain.repository

import com.sample.android.tmdb.domain.model.CreditWrapper
import com.sample.android.tmdb.domain.model.Video
import io.reactivex.Single

interface TVShowDetailRepository {

    fun getTVShowTrailers(tvId: Int): Single<List<Video>>

    fun getTVShowCredit(tvId: Int): CreditWrapper
}