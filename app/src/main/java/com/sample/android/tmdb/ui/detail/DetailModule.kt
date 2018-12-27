package com.sample.android.tmdb.ui.detail

import com.sample.android.tmdb.di.ActivityScoped
import com.sample.android.tmdb.di.FragmentScoped
import com.sample.android.tmdb.ui.detail.DetailActivity.Companion.EXTRA_MOVIE
import com.sample.android.tmdb.vo.Movie
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class DetailModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun detailFragment(): DetailFragment

    @Module
    companion object {
        @Provides
        @ActivityScoped
        @JvmStatic internal fun provideMovie(activity: DetailActivity): Movie =
                activity.intent.extras.getParcelable(EXTRA_MOVIE)
    }
}
