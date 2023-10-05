package com.sample.android.tmdb.data.repository

import com.sample.android.tmdb.data.network.MovieService
import com.sample.android.tmdb.data.response.asCreditWrapper
import com.sample.android.tmdb.data.response.asDomainModel
import com.sample.android.tmdb.domain.repository.MovieDetailRepository
import com.sample.android.tmdb.domain.model.CreditWrapper
import com.sample.android.tmdb.domain.model.Video
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDetailRepositoryImpl @Inject constructor(
    private val movieApi: MovieService
) : MovieDetailRepository {

    override fun getMovieTrailers(movieId: Int): Single<List<Video>> =
        movieApi.movieTrailers(movieId).map { it.videos.asDomainModel() }

    override fun getMovieCredit(movieId: Int): CreditWrapper =
         movieApi.movieCredit(movieId).asCreditWrapper()
}