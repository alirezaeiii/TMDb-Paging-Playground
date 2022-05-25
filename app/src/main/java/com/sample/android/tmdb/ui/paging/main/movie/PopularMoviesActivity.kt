package com.sample.android.tmdb.ui.paging.main.movie

import com.sample.android.tmdb.R
import javax.inject.Inject

class PopularMoviesActivity: MoviesActivity() {

    @Inject
    lateinit var popularMoviesFragment: PopularMoviesFragment

    override val titleId: Int
        get() = R.string.popular

    override val fragment: MovieFragment
        get() = popularMoviesFragment
}