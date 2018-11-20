package com.sample.android.tmdb.di

import com.sample.android.tmdb.ui.MainActivity
import com.sample.android.tmdb.ui.base.MoviesModule
import com.sample.android.tmdb.ui.detail.DetailActivity
import com.sample.android.tmdb.ui.detail.DetailModule
import com.sample.android.tmdb.ui.search.SearchActivity
import com.sample.android.tmdb.ui.search.SearchModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [MoviesModule::class])
    internal abstract fun mainActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [SearchModule::class])
    internal abstract fun searchActivity(): SearchActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [DetailModule::class])
    internal abstract fun detailActivity(): DetailActivity
}