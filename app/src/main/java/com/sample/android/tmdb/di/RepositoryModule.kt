package com.sample.android.tmdb.di

import com.sample.android.tmdb.data.repository.MovieDetailRepositoryImpl
import com.sample.android.tmdb.data.repository.MovieFeedRepository
import com.sample.android.tmdb.data.repository.PersonRepositoryImpl
import com.sample.android.tmdb.data.repository.TVShowDetailRepositoryImpl
import com.sample.android.tmdb.data.repository.TVShowFeedRepository
import com.sample.android.tmdb.domain.model.Movie
import com.sample.android.tmdb.domain.model.TVShow
import com.sample.android.tmdb.domain.repository.BaseFeedRepository
import com.sample.android.tmdb.domain.repository.MovieDetailRepository
import com.sample.android.tmdb.domain.repository.PersonRepository
import com.sample.android.tmdb.domain.repository.TVShowDetailRepository
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