package com.sample.android.tmdb.di.movie

import com.sample.android.tmdb.ui.paging.main.movie.TrendingMoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TrendingMoviesModule {

    @ContributesAndroidInjector
    internal abstract fun trendingMoviesFragment(): TrendingMoviesFragment
}