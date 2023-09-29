package com.sample.android.tmdb.di.tvshow

import com.sample.android.tmdb.ui.paging.main.tvshow.DiscoverTVShowsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DiscoverTVShowModule {

    @ContributesAndroidInjector
    internal abstract fun discoverTVShowFragment(): DiscoverTVShowsFragment
}