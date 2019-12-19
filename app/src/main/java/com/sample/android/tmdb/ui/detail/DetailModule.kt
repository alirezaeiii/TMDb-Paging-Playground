package com.sample.android.tmdb.ui.detail

import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.ui.detail.movie.MovieDetailFragment
import com.sample.android.tmdb.ui.detail.tvshow.TVShowDetailFragment
import com.sample.android.tmdb.util.NavType
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
        internal fun provideMovie(activity: DetailActivity): Movie? =
                activity.intent.extras.getParcelable(EXTRA_MOVIE)

        @Provides
        @JvmStatic
        internal fun provideTVShow(activity: DetailActivity): TVShow? =
                activity.intent.extras.getParcelable(EXTRA_TV_SHOW)

        @Provides
        @JvmStatic
        internal fun provideNavType(activity: DetailActivity): NavType =
                activity.intent.extras.getParcelable(EXTRA_NAV_TYPE)
    }
}
