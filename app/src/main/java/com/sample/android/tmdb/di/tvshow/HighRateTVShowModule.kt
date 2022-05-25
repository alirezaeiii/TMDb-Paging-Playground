package com.sample.android.tmdb.di.tvshow

import com.sample.android.tmdb.ui.paging.main.tvshow.HighRateTVShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HighRateTVShowModule {

    @ContributesAndroidInjector
    internal abstract fun highRateTVShowFragment(): HighRateTVShowFragment
}