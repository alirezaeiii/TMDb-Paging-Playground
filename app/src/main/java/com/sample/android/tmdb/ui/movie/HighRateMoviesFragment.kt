package com.sample.android.tmdb.ui.movie

import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.SortType.HIGHEST_RATED
import com.sample.android.tmdb.di.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class HighRateMoviesFragment @Inject
constructor() : MovieFragment() {

    override fun getSortType(): SortType = HIGHEST_RATED
}