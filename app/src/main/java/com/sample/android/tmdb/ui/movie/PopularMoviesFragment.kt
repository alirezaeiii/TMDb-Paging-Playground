package com.sample.android.tmdb.ui.movie

import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.SortType.MOST_POPULAR
import com.sample.android.tmdb.di.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PopularMoviesFragment @Inject
constructor() : MovieFragment() {

    override fun getSortType(): SortType = MOST_POPULAR
}