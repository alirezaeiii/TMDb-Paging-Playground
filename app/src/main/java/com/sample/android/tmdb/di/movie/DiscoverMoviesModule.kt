package com.sample.android.tmdb.di.movie

import com.sample.android.tmdb.ui.paging.main.movie.DiscoverMoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DiscoverMoviesModule {

    @ContributesAndroidInjector
    internal abstract fun discoverMoviesFragment(): DiscoverMoviesFragment
}