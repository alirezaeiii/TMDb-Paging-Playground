package com.sample.android.tmdb.repository

import com.sample.android.tmdb.data.network.MovieApi
import com.sample.android.tmdb.data.response.asCastDomainModel
import com.sample.android.tmdb.data.response.asCrewDomainModel
import com.sample.android.tmdb.data.response.asDomainModel
import com.sample.android.tmdb.domain.MovieDetailRepository
import com.sample.android.tmdb.domain.model.CreditWrapper
import com.sample.android.tmdb.domain.model.Video
import io.reactivex.Single
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieDetailRepository {

    override fun getMovieTrailers(movieId: Int): Single<List<Video>> =
        movieApi.movieTrailers(movieId).map { it.videos.asDomainModel() }

    override fun getMovieCredit(movieId: Int): CreditWrapper {
        val networkCreditWrapper = movieApi.movieCredit(movieId)
        val cast = networkCreditWrapper.map { it.cast.asCastDomainModel() }
        val crew = networkCreditWrapper.map { it.crew.asCrewDomainModel() }
        return CreditWrapper(cast, crew)
    }
}