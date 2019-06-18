package com.sample.android.tmdb.ui.base

import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.SortType.UPCOMING
import com.sample.android.tmdb.di.ActivityScoped
import com.sample.android.tmdb.ui.MovieFragment
import com.sample.android.tmdb.ui.TVShowFragment
import javax.inject.Inject

@ActivityScoped
class LatestTVShowFragment @Inject
constructor() : TVShowFragment() {

    override fun getSortType(): SortType = UPCOMING
}