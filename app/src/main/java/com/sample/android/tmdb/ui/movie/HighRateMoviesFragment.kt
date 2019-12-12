package com.sample.android.tmdb.ui.movie

import com.sample.android.tmdb.di.ActivityScoped
import com.sample.android.tmdb.util.SortType.HIGHEST_RATED
import javax.inject.Inject

@ActivityScoped
class HighRateMoviesFragment @Inject
constructor() : MovieFragment() {

    override val sortType= HIGHEST_RATED
}