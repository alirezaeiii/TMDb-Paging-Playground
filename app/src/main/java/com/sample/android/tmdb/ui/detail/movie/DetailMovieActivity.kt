package com.sample.android.tmdb.ui.detail.movie

import com.sample.android.tmdb.ui.detail.DetailActivity
import com.sample.android.tmdb.ui.detail.DetailFragment
import javax.inject.Inject

class DetailMovieActivity: DetailActivity() {

    @Inject
    lateinit var detailMovieFragment: DetailMovieFragment

    override val fragment: DetailFragment
        get() = detailMovieFragment
}