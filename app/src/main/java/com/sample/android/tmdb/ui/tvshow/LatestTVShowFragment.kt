package com.sample.android.tmdb.ui.tvshow

import com.sample.android.tmdb.di.ActivityScoped
import com.sample.android.tmdb.util.SortType.UPCOMING
import javax.inject.Inject

@ActivityScoped
class LatestTVShowFragment @Inject
constructor() : TVShowFragment() {

    override val sortType = UPCOMING
}