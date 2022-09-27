package com.sample.android.tmdb.di.tvshow

import com.sample.android.tmdb.ui.paging.main.tvshow.OnTheAirTVShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class OnTheAirTVShowModule {

    @ContributesAndroidInjector
    internal abstract fun onTheAirTVShowFragment(): OnTheAirTVShowFragment
}