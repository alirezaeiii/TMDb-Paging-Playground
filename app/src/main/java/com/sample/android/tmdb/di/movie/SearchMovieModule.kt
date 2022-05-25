package com.sample.android.tmdb.di.movie

import com.sample.android.tmdb.ui.paging.search.movie.SearchMovieFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchMovieModule {

    @ContributesAndroidInjector
    internal abstract fun searchMovieFragment(): SearchMovieFragment
}