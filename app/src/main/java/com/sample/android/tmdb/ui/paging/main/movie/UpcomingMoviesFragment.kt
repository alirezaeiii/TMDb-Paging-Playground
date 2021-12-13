package com.sample.android.tmdb.ui.paging.main.movie

import com.sample.android.tmdb.ui.paging.main.SortType.UPCOMING
import javax.inject.Inject

class UpcomingMoviesFragment @Inject
constructor() : MovieFragment() {

    override val sortType = UPCOMING
}