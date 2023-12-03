package com.sample.android.tmdb.ui.paging.main.movie

import com.sample.android.tmdb.R
import javax.inject.Inject

class TrendingMoviesActivity: MoviePagingActivity() {

    @Inject
    lateinit var trendingMoviesFragment: TrendingMoviesFragment

    override val titleId: Int
        get() = R.string.trending

    override val fragment: MoviePagingFragment
        get() = trendingMoviesFragment
}