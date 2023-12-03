package com.sample.android.tmdb.ui.paging.main.movie

import com.sample.android.tmdb.R
import javax.inject.Inject

class NowPlayingMoviesActivity : MoviePagingActivity() {

    @Inject
    lateinit var nowPlayingMoviesFragment: NowPlayingMoviesFragment

    override val titleId: Int
        get() = R.string.now_playing

    override val fragment: MoviePagingFragment
        get() = nowPlayingMoviesFragment
}