package com.sample.android.tmdb.di

import com.sample.android.tmdb.ui.detail.DetailActivity
import com.sample.android.tmdb.ui.feed.FeedActivity
import com.sample.android.tmdb.ui.paging.main.MainActivity
import com.sample.android.tmdb.ui.person.PersonActivity
import com.sample.android.tmdb.ui.paging.search.SearchActivity
import com.sample.android.tmdb.ui.start.StartActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [InAppUpdateModule::class])
    internal abstract fun startActivity(): StartActivity

    @ContributesAndroidInjector(modules = [FeedModule::class])
    internal abstract fun feedActivity(): FeedActivity

    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [SearchModule::class])
    internal abstract fun searchActivity(): SearchActivity

    @ContributesAndroidInjector(modules = [DetailModule::class])
    internal abstract fun detailActivity(): DetailActivity

    @ContributesAndroidInjector(modules = [PersonModule::class])
    internal abstract fun personActivity(): PersonActivity
}