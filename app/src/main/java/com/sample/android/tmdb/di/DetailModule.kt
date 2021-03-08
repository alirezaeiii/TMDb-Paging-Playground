package com.sample.android.tmdb.di

import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.ui.NavType
import com.sample.android.tmdb.ui.detail.DetailActivity
import com.sample.android.tmdb.ui.detail.EXTRA_NAV_TYPE
import com.sample.android.tmdb.ui.detail.EXTRA_TMDB_ITEM
import com.sample.android.tmdb.ui.detail.movie.MovieDetailFragment
import com.sample.android.tmdb.ui.detail.tvshow.TVShowDetailFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class DetailModule {

    @ContributesAndroidInjector
    internal abstract fun movieDetailFragment(): MovieDetailFragment

    @ContributesAndroidInjector
    internal abstract fun tvShowDetailFragment(): TVShowDetailFragment

    @Module
    companion object {
        @Provides
        @JvmStatic
        internal fun provideTmdbItem(activity: DetailActivity): TmdbItem =
                activity.intent.extras?.getParcelable(EXTRA_TMDB_ITEM)!!

        @Provides
        @JvmStatic
        internal fun provideNavType(activity: DetailActivity): NavType =
                activity.intent.extras?.getParcelable(EXTRA_NAV_TYPE)!!
    }
}
