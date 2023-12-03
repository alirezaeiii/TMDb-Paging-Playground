package com.sample.android.tmdb.ui.paging.main.movie

import com.sample.android.tmdb.domain.model.SortType
import javax.inject.Inject

class NowPlayingMoviesFragment @Inject
constructor() : MoviePagingFragment() {

    override val sortType = SortType.NOW_PLAYING
}