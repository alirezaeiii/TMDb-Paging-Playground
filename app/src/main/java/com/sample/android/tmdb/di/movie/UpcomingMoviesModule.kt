package com.sample.android.tmdb.di.movie

import com.sample.android.tmdb.ui.paging.main.movie.UpcomingMoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UpcomingMoviesModule {

    @ContributesAndroidInjector
    internal abstract fun upcomingMoviesFragment(): UpcomingMoviesFragment
}