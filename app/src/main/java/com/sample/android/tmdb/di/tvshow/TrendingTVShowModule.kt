package com.sample.android.tmdb.di.tvshow

import com.sample.android.tmdb.ui.paging.main.tvshow.TrendingTVShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TrendingTVShowModule {

    @ContributesAndroidInjector
    internal abstract fun trendingTVShowFragment(): TrendingTVShowFragment
}