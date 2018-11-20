package com.sample.android.tmdb.ui.search

import com.sample.android.tmdb.di.FragmentScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun searchFragment(): SearchFragment
}