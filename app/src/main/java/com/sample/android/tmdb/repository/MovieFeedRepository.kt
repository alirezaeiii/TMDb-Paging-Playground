package com.sample.android.tmdb.repository

import android.content.Context
import com.sample.android.tmdb.R
import com.sample.android.tmdb.di.IoDispatcher
import com.sample.android.tmdb.data.ItemWrapper
import com.sample.android.tmdb.data.Movie
import com.sample.android.tmdb.domain.BaseFeedRepository
import com.sample.android.tmdb.network.MovieApi
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieFeedRepository @Inject constructor(
    context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val movieApi: MovieApi,
): BaseFeedRepository<Movie>(context, ioDispatcher) {

    override suspend fun popularItems(): ItemWrapper<Movie> = movieApi.popularMovies()

    override suspend fun latestItems(): ItemWrapper<Movie> = movieApi.upcomingMovies()

    override suspend fun topRatedItems(): ItemWrapper<Movie> = movieApi.topRatedMovies()

    override suspend fun trendingItems(): ItemWrapper<Movie> = movieApi.trendingMovies()

    override suspend fun nowPlayingItems(): ItemWrapper<Movie> = movieApi.nowPlayingMovies()

    override fun getNowPlayingResId(): Int = R.string.text_now_playing

    override fun getLatestResId(): Int = R.string.text_upcoming
}