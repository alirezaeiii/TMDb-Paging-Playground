package com.sample.android.tmdb.ui

import com.sample.android.tmdb.di.FragmentScoped
import com.sample.android.tmdb.ui.tvshow.*
import com.sample.android.tmdb.ui.movie.HighRateMoviesFragment
import com.sample.android.tmdb.ui.movie.PopularMoviesFragment
import com.sample.android.tmdb.ui.movie.UpcomingMoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ItemModule {

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
    internal abstract fun popularTVShowFragment(): PopularTVShowBaseFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun highRateTVShowFragment(): HighRateTVShowBaseFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun latestTVShowFragment(): LatestTVShowBaseFragment
}