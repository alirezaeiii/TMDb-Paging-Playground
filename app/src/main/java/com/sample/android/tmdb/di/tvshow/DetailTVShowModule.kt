package com.sample.android.tmdb.di.tvshow

import com.sample.android.tmdb.domain.model.TmdbItem
import com.sample.android.tmdb.ui.detail.tvshow.DetailTVShowActivity
import com.sample.android.tmdb.ui.detail.tvshow.DetailTVShowFragment
import com.sample.android.tmdb.util.Constants.EXTRA_TMDB_ITEM
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class DetailTVShowModule {

    @ContributesAndroidInjector
    internal abstract fun tvShowDetailFragment(): DetailTVShowFragment

    @Module
    companion object {
        @Provides
        @JvmStatic
        internal fun provideTmdbItem(activity: DetailTVShowActivity): TmdbItem =
            activity.intent.extras?.getParcelable(EXTRA_TMDB_ITEM)!!
    }
}