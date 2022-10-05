package com.sample.android.tmdb.domain

import com.sample.android.tmdb.data.CreditWrapper
import com.sample.android.tmdb.data.VideoWrapper
import io.reactivex.Single

interface MovieDetailRepository {

    fun getMovieTrailers(movieId: Int): Single<VideoWrapper>

    fun getMovieCredit(movieId: Int): Single<CreditWrapper>
}