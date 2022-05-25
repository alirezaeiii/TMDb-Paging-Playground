package com.sample.android.tmdb.di.tvshow

import com.sample.android.tmdb.ui.paging.search.tvshow.SearchTVShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchTVShowModule {

    @ContributesAndroidInjector
    internal abstract fun searchTVShowFragment(): SearchTVShowFragment
}