package com.sample.android.tmdb.ui.paging.main

import android.os.Bundle
import com.sample.android.tmdb.ui.feed.NavType
import com.sample.android.tmdb.ui.paging.main.movie.HighRateMoviesFragment
import com.sample.android.tmdb.ui.paging.main.movie.PopularMoviesFragment
import com.sample.android.tmdb.ui.paging.main.movie.UpcomingMoviesFragment
import com.sample.android.tmdb.ui.paging.main.tvshow.HighRateTVShowFragment
import com.sample.android.tmdb.ui.paging.main.tvshow.LatestTVShowFragment
import com.sample.android.tmdb.ui.paging.main.tvshow.PopularTVShowFragment
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity: DaggerAppCompatActivity() {

    @Inject
    lateinit var popularMoviesFragment: PopularMoviesFragment

    @Inject
    lateinit var highRateMoviesFragment: HighRateMoviesFragment

    @Inject
    lateinit var upcomingMoviesFragment: UpcomingMoviesFragment

    @Inject
    lateinit var popularTvShowFragment: PopularTVShowFragment

    @Inject
    lateinit var highRateTvShowFragment: HighRateTVShowFragment

    @Inject
    lateinit var latestTvShowFragment: LatestTVShowFragment

    @Inject
    lateinit var navType: NavType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}