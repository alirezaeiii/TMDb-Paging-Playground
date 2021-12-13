package com.sample.android.tmdb.ui.paging.main.movie

import com.sample.android.tmdb.ui.paging.main.SortType.MOST_POPULAR
import javax.inject.Inject

class PopularMoviesFragment @Inject
constructor() : MovieFragment() {

    override val sortType = MOST_POPULAR
}