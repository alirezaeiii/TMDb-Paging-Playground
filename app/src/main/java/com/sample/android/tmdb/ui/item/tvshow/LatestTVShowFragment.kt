package com.sample.android.tmdb.ui.item.tvshow

import com.sample.android.tmdb.ui.item.SortType.UPCOMING
import javax.inject.Inject

class LatestTVShowFragment @Inject
constructor() : TVShowFragment() {

    override val sortType = UPCOMING
}