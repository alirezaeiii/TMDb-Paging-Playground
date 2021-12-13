package com.sample.android.tmdb.ui.paging.main.movie

import com.sample.android.tmdb.ui.paging.main.SortType.HIGHEST_RATED
import javax.inject.Inject

class HighRateMoviesFragment @Inject
constructor() : MovieFragment() {

    override val sortType = HIGHEST_RATED
}