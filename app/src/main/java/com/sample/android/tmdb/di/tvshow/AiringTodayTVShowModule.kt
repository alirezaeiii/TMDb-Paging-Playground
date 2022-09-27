package com.sample.android.tmdb.di.tvshow

import com.sample.android.tmdb.ui.paging.main.tvshow.AiringTodayTVShowsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AiringTodayTVShowModule {

    @ContributesAndroidInjector
    internal abstract fun airingTodayTVShowFragment(): AiringTodayTVShowsFragment
}