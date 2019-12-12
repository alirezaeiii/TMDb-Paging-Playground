package com.sample.android.tmdb.ui.movie

import com.sample.android.tmdb.di.ActivityScoped
import com.sample.android.tmdb.util.SortType.UPCOMING
import javax.inject.Inject

@ActivityScoped
class UpcomingMoviesFragment @Inject
constructor() : MovieFragment() {

    override val sortType = UPCOMING
}