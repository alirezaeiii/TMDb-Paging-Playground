package com.sample.android.tmdb.di

import com.sample.android.tmdb.ui.MainActivity
import com.sample.android.tmdb.ui.ItemModule
import com.sample.android.tmdb.ui.detail.DetailActivity
import com.sample.android.tmdb.ui.detail.DetailModule
import com.sample.android.tmdb.ui.person.PersonActivity
import com.sample.android.tmdb.ui.person.PersonModule
import com.sample.android.tmdb.ui.search.SearchActivity
import com.sample.android.tmdb.ui.search.SearchModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [ItemModule::class])
    internal abstract fun mainActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [SearchModule::class])
    internal abstract fun searchActivity(): SearchActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [DetailModule::class])
    internal abstract fun detailActivity(): DetailActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [PersonModule::class])
    internal abstract fun personActivity(): PersonActivity
}