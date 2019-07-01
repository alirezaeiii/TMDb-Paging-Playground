package com.sample.android.tmdb.ui.detail

import com.sample.android.tmdb.NavType
import com.sample.android.tmdb.di.ActivityScoped
import com.sample.android.tmdb.di.FragmentScoped
import com.sample.android.tmdb.ui.detail.DetailActivity.Companion.EXTRA_ITEM
import com.sample.android.tmdb.ui.detail.DetailActivity.Companion.EXTRA_NAV_TYPE
import com.sample.android.tmdb.ui.detail.movie.MovieDetailFragment
import com.sample.android.tmdb.ui.detail.tvshow.TVShowDetailFragment
import com.sample.android.tmdb.vo.Movie
import com.sample.android.tmdb.vo.TVShow
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class DetailModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun movieDetailFragment(): MovieDetailFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun tvShowDetailFragment(): TVShowDetailFragment

    @Module
    companion object {
        @Provides
        @ActivityScoped
        @JvmStatic
        internal fun provideMovie(activity: DetailActivity): Movie? =
                activity.intent.extras.getParcelable(EXTRA_ITEM)

        @Provides
        @ActivityScoped
        @JvmStatic
        internal fun provideTVShow(activity: DetailActivity): TVShow? =
                activity.intent.extras.getParcelable(EXTRA_ITEM)

        @Provides
        @ActivityScoped
        @JvmStatic
        internal fun provideNavType(activity: DetailActivity): NavType =
                activity.intent.extras.getParcelable(EXTRA_NAV_TYPE)
    }
}
