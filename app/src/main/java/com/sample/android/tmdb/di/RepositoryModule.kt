package com.sample.android.tmdb.di

import com.sample.android.tmdb.data.Movie
import com.sample.android.tmdb.data.TVShow
import com.sample.android.tmdb.domain.MovieDetailRepository
import com.sample.android.tmdb.domain.PersonRepository
import com.sample.android.tmdb.domain.TVShowDetailRepository
import com.sample.android.tmdb.domain.BaseFeedRepository
import com.sample.android.tmdb.repository.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    internal abstract fun bindMovieFeedRepository(movieFeedRepository: MovieFeedRepository): BaseFeedRepository<Movie>

    @Singleton
    @Binds
    internal abstract fun bindTVShowFeedRepository(tvShowFeedRepository: TVShowFeedRepository): BaseFeedRepository<TVShow>

    @Singleton
    @Binds
    internal abstract fun bindMovieDetailRepository(movieDetailRepository: MovieDetailRepositoryImpl): MovieDetailRepository

    @Singleton
    @Binds
    internal abstract fun bindTVShowDetailRepository(tvShowDetailRepository: TVShowDetailRepositoryImpl): TVShowDetailRepository

    @Singleton
    @Binds
    internal abstract fun bindPersonRepository(personRepository: PersonRepositoryImpl): PersonRepository
}