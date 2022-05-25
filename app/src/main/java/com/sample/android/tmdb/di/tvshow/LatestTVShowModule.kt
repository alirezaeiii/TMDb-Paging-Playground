package com.sample.android.tmdb.di.tvshow

import com.sample.android.tmdb.ui.paging.main.tvshow.LatestTVShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LatestTVShowModule {

    @ContributesAndroidInjector
    internal abstract fun latestTVShowFragment(): LatestTVShowFragment
}