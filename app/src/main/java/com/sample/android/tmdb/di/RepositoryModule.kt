package com.sample.android.tmdb.di

import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.repository.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    internal abstract fun bindMovieRepository(movieRepository: MovieRepository): TmdbRepository<Movie>

    @Singleton
    @Binds
    internal abstract fun bindTVShowRepository(tvShowRepository: TVShowRepository): TmdbRepository<TVShow>

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