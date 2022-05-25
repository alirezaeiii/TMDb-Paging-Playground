package com.sample.android.tmdb.di.tvshow

import com.sample.android.tmdb.ui.paging.main.tvshow.PopularTVShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PopularTVShowModule {

    @ContributesAndroidInjector
    internal abstract fun popularTVShowFragment(): PopularTVShowFragment
}