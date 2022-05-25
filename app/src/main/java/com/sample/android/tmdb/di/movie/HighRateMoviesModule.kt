package com.sample.android.tmdb.di.movie

import com.sample.android.tmdb.ui.paging.main.movie.HighRateMoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HighRateMoviesModule {

    @ContributesAndroidInjector
    internal abstract fun highRateMoviesFragment(): HighRateMoviesFragment
}