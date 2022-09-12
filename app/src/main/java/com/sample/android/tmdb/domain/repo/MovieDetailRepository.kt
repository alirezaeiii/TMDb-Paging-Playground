package com.sample.android.tmdb.domain.repo

import com.sample.android.tmdb.domain.CreditWrapper
import com.sample.android.tmdb.domain.VideoWrapper
import io.reactivex.Single

interface MovieDetailRepository {

    fun getMovieTrailers(movieId: Int): Single<VideoWrapper>

    fun getMovieCredit(movieId: Int): Single<CreditWrapper>
}