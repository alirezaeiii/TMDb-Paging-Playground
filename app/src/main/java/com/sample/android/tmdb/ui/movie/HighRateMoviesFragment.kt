package com.sample.android.tmdb.ui.movie

import com.sample.android.tmdb.util.SortType.HIGHEST_RATED
import javax.inject.Inject

class HighRateMoviesFragment @Inject
constructor() : MainMovieFragment() {

    override val sortType= HIGHEST_RATED
}