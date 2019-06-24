package com.sample.android.tmdb.ui.search

import com.sample.android.tmdb.NavType
import com.sample.android.tmdb.di.ActivityScoped
import com.sample.android.tmdb.di.FragmentScoped
import com.sample.android.tmdb.ui.search.SearchActivity.Companion.EXTRA_NAV_TYPE
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun searchMovieFragment(): SearchMovieFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun searchTVShowFragment(): SearchTVShowFragment

    @Module
    companion object {
        @Provides
        @ActivityScoped
        @JvmStatic
        internal fun provideNavType(activity: SearchActivity): NavType =
                activity.intent.extras.getParcelable(EXTRA_NAV_TYPE)
    }
}