package com.sample.android.tmdb.ui.base

import com.sample.android.tmdb.di.FragmentScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MoviesModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun popularMoviesFragment(): PopularMoviesFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun highRateMoviesFragment(): HighRateMoviesFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun upcomingMoviesFragment(): UpcomingMoviesFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun popularTVShowFragment(): PopularTVShowFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun highRateTVShowFragment(): HighRateTVShowFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun latestTVShowFragment(): LatestTVShowFragment
}