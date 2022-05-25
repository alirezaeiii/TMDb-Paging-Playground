package com.sample.android.tmdb.ui.paging.main.movie

import com.sample.android.tmdb.R
import javax.inject.Inject

class HighRateMoviesActivity: MoviesActivity() {

    @Inject
    lateinit var highRateMoviesFragment: HighRateMoviesFragment

    override val titleId: Int
        get() = R.string.highest_rate

    override val fragment: MovieFragment
        get() = highRateMoviesFragment
}