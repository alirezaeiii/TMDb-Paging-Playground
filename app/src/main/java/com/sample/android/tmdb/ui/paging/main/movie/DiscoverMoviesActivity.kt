package com.sample.android.tmdb.ui.paging.main.movie

import com.sample.android.tmdb.R
import javax.inject.Inject

class DiscoverMoviesActivity: MoviePagingActivity() {

    @Inject
    lateinit var discoverMoviesFragment: DiscoverMoviesFragment

    override val titleId: Int
        get() = R.string.discover

    override val fragment: MoviePagingFragment
        get() = discoverMoviesFragment
}