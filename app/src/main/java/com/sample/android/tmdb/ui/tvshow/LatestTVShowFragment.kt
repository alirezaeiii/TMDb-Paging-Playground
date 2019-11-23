package com.sample.android.tmdb.ui.tvshow

import com.sample.android.tmdb.util.SortType
import com.sample.android.tmdb.util.SortType.UPCOMING
import com.sample.android.tmdb.di.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class LatestTVShowFragment @Inject
constructor() : TVShowFragment() {

    override fun getSortType(): SortType = UPCOMING
}