package com.sample.android.tmdb.di

import com.sample.android.tmdb.ui.feed.FeedMovieFragment
import com.sample.android.tmdb.ui.feed.FeedTVShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FeedModule {

    @ContributesAndroidInjector
    internal abstract fun feedMovieFragment(): FeedMovieFragment

    @ContributesAndroidInjector
    internal abstract fun feedTVShowFragment(): FeedTVShowFragment
}