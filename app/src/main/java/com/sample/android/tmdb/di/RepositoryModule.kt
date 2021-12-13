package com.sample.android.tmdb.di

import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.repository.MovieRepository
import com.sample.android.tmdb.repository.TVShowRepository
import com.sample.android.tmdb.repository.TmdbRepository
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
}