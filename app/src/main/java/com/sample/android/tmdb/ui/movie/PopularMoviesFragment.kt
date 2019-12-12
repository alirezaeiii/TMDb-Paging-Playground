package com.sample.android.tmdb.ui.movie

import com.sample.android.tmdb.di.ActivityScoped
import com.sample.android.tmdb.util.SortType.MOST_POPULAR
import javax.inject.Inject

@ActivityScoped
class PopularMoviesFragment @Inject
constructor() : MovieFragment() {

    override val sortType= MOST_POPULAR
}