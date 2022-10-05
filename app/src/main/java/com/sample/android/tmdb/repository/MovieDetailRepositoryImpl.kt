package com.sample.android.tmdb.repository

import com.sample.android.tmdb.domain.MovieDetailRepository
import com.sample.android.tmdb.network.MovieApi
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieDetailRepository {

    override fun getMovieTrailers(movieId: Int) = movieApi.movieTrailers(movieId)

    override fun getMovieCredit(movieId: Int) = movieApi.movieCredit(movieId)
}