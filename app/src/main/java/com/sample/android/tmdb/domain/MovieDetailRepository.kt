package com.sample.android.tmdb.domain

import com.sample.android.tmdb.data.response.VideoWrapper
import com.sample.android.tmdb.domain.model.CreditWrapper
import io.reactivex.Single

interface MovieDetailRepository {

    fun getMovieTrailers(movieId: Int): Single<VideoWrapper>

    fun getMovieCredit(movieId: Int): CreditWrapper
}