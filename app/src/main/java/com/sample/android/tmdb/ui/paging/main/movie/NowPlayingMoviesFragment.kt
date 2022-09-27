package com.sample.android.tmdb.ui.paging.main.movie

import com.sample.android.tmdb.ui.paging.main.SortType
import javax.inject.Inject

class NowPlayingMoviesFragment @Inject
constructor() : MovieFragment() {

    override val sortType = SortType.NOW_PLAYING
}