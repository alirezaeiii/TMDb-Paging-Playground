package com.sample.android.tmdb.ui.movie

import com.sample.android.tmdb.util.SortType.MOST_POPULAR
import javax.inject.Inject

class PopularMoviesFragment @Inject
constructor() : MovieFragment() {

    override val sortType = MOST_POPULAR
}