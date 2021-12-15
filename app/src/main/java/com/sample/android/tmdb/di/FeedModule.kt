package com.sample.android.tmdb.di

import com.sample.android.tmdb.ui.feed.movie.FeedMovieFragment
import com.sample.android.tmdb.ui.feed.tvshow.FeedTVShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FeedModule {

    @ContributesAndroidInjector
    internal abstract fun feedMovieFragment(): FeedMovieFragment

    @ContributesAndroidInjector
    internal abstract fun feedTVShowFragment(): FeedTVShowFragment
}