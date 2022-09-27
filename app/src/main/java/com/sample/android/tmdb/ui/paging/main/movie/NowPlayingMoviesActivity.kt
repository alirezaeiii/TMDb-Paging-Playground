package com.sample.android.tmdb.ui.paging.main.movie

import com.sample.android.tmdb.R
import javax.inject.Inject

class NowPlayingMoviesActivity : MoviesActivity() {

    @Inject
    lateinit var nowPlayingMoviesFragment: NowPlayingMoviesFragment

    override val titleId: Int
        get() = R.string.now_playing

    override val fragment: MovieFragment
        get() = nowPlayingMoviesFragment
}