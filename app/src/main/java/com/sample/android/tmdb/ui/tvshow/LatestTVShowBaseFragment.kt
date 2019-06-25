package com.sample.android.tmdb.ui.tvshow

import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.SortType.UPCOMING
import com.sample.android.tmdb.di.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class LatestTVShowBaseFragment @Inject
constructor() : TVShowBaseFragment() {

    override fun getSortType(): SortType = UPCOMING
}