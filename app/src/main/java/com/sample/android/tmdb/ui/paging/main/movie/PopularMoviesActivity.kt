package com.sample.android.tmdb.ui.paging.main.movie

import com.sample.android.tmdb.R
import javax.inject.Inject

class PopularMoviesActivity: MoviePagingActivity() {

    @Inject
    lateinit var popularMoviesFragment: PopularMoviesFragment

    override val titleId: Int
        get() = R.string.popular

    override val fragment: MoviePagingFragment
        get() = popularMoviesFragment
}