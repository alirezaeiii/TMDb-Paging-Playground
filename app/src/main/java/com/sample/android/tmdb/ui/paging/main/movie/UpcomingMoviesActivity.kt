package com.sample.android.tmdb.ui.paging.main.movie

import com.sample.android.tmdb.R
import javax.inject.Inject

class UpcomingMoviesActivity: MoviesActivity() {

    @Inject
    lateinit var upcomingMoviesFragment: UpcomingMoviesFragment

    override val titleId: Int
        get() = R.string.upcoming

    override val fragment: MovieFragment
        get() = upcomingMoviesFragment
}