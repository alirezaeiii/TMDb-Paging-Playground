package com.sample.android.tmdb.ui.item.movie

import com.sample.android.tmdb.ui.item.SortType.HIGHEST_RATED
import javax.inject.Inject

class HighRateMoviesFragment @Inject
constructor() : MovieFragment() {

    override val sortType = HIGHEST_RATED
}