package com.sample.android.tmdb.repository

import android.content.Context
import com.sample.android.tmdb.domain.ItemWrapper
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.network.MovieApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    context: Context,
    private val movieApi: MovieApi,
): TmdbRepository<Movie>(context) {

    override suspend fun popularItems(): ItemWrapper<Movie> = movieApi.popularMovies()

    override suspend fun latestItems(): ItemWrapper<Movie> = movieApi.latestMovies()

    override suspend fun topRatedItems(): ItemWrapper<Movie> = movieApi.topRatedMovies()

    override suspend fun trendingItems(): ItemWrapper<Movie> = movieApi.trendingMovies()
}