package com.sample.android.tmdb.domain

import com.sample.android.tmdb.domain.model.CreditWrapper
import com.sample.android.tmdb.domain.model.Video
import io.reactivex.Single

interface MovieDetailRepository {

    fun getMovieTrailers(movieId: Int): Single<List<Video>>

    fun getMovieCredit(movieId: Int): CreditWrapper
}