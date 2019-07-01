package com.sample.android.tmdb.ui.movie

import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.SortType.UPCOMING
import com.sample.android.tmdb.di.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class UpcomingMoviesFragment @Inject
constructor() : MovieBaseFragment() {

    override fun getSortType(): SortType = UPCOMING
}