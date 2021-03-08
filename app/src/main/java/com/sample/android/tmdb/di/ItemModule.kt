package com.sample.android.tmdb.di

import com.sample.android.tmdb.ui.item.tvshow.*
import com.sample.android.tmdb.ui.item.movie.HighRateMoviesFragment
import com.sample.android.tmdb.ui.item.movie.PopularMoviesFragment
import com.sample.android.tmdb.ui.item.movie.UpcomingMoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ItemModule {

    @ContributesAndroidInjector
    internal abstract fun popularMoviesFragment(): PopularMoviesFragment

    @ContributesAndroidInjector
    internal abstract fun highRateMoviesFragment(): HighRateMoviesFragment

    @ContributesAndroidInjector
    internal abstract fun upcomingMoviesFragment(): UpcomingMoviesFragment

    @ContributesAndroidInjector
    internal abstract fun popularTVShowFragment(): PopularTVShowFragment

    @ContributesAndroidInjector
    internal abstract fun highRateTVShowFragment(): HighRateTVShowFragment

    @ContributesAndroidInjector
    internal abstract fun latestTVShowFragment(): LatestTVShowFragment
}