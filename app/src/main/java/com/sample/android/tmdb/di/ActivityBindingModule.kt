package com.sample.android.tmdb.di

import com.sample.android.tmdb.ui.MainActivity
import com.sample.android.tmdb.ui.detail.DetailActivity
import com.sample.android.tmdb.ui.person.PersonActivity
import com.sample.android.tmdb.ui.search.SearchActivity
import com.sample.android.tmdb.ui.start.StartActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [InAppUpdateModule::class])
    internal abstract fun startActivity(): StartActivity

    @ContributesAndroidInjector(modules = [ItemModule::class])
    internal abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [SearchModule::class])
    internal abstract fun searchActivity(): SearchActivity

    @ContributesAndroidInjector(modules = [DetailModule::class])
    internal abstract fun detailActivity(): DetailActivity

    @ContributesAndroidInjector(modules = [PersonModule::class])
    internal abstract fun personActivity(): PersonActivity
}