package com.sample.android.tmdb.ui.movie

import com.sample.android.tmdb.util.SortType
import com.sample.android.tmdb.util.SortType.UPCOMING
import com.sample.android.tmdb.di.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class UpcomingMoviesFragment @Inject
constructor() : MovieFragment() {

    override fun getSortType(): SortType = UPCOMING
}