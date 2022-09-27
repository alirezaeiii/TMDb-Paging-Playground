package com.sample.android.tmdb.di.movie

import com.sample.android.tmdb.ui.paging.main.movie.NowPlayingMoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NowPlayingMoviesModule {

    @ContributesAndroidInjector
    internal abstract fun nowPlayingMoviesFragment(): NowPlayingMoviesFragment
}