package com.sample.android.tmdb.repository

import android.content.Context
import com.sample.android.tmdb.R
import com.sample.android.tmdb.data.response.asMovieDomainModel
import com.sample.android.tmdb.di.IoDispatcher
import com.sample.android.tmdb.domain.repository.BaseFeedRepository
import com.sample.android.tmdb.domain.model.Movie
import com.sample.android.tmdb.data.network.MovieService
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieFeedRepository @Inject constructor(
    context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val movieApi: MovieService,
): BaseFeedRepository<Movie>(context, ioDispatcher) {

    override suspend fun popularItems(): List<Movie> = movieApi.popularMovies().items.asMovieDomainModel()

    override suspend fun latestItems(): List<Movie> = movieApi.upcomingMovies().items.asMovieDomainModel()

    override suspend fun topRatedItems(): List<Movie> = movieApi.topRatedMovies().items.asMovieDomainModel()

    override suspend fun trendingItems(): List<Movie> = movieApi.trendingMovies().items.asMovieDomainModel()

    override suspend fun nowPlayingItems(): List<Movie> = movieApi.nowPlayingMovies().items.asMovieDomainModel()

    override suspend fun discoverItems(): List<Movie> = movieApi.discoverMovies().items.asMovieDomainModel()

    override fun getNowPlayingResId(): Int = R.string.text_now_playing

    override fun getLatestResId(): Int = R.string.text_upcoming
}