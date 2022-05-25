package com.sample.android.tmdb.di.movie

import com.sample.android.tmdb.ui.paging.main.movie.PopularMoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PopularMoviesModule {

    @ContributesAndroidInjector
    internal abstract fun popularMoviesFragment(): PopularMoviesFragment
}