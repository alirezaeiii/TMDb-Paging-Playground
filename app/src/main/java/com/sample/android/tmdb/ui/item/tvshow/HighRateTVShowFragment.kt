package com.sample.android.tmdb.ui.item.tvshow

import com.sample.android.tmdb.ui.item.SortType.HIGHEST_RATED
import javax.inject.Inject

class HighRateTVShowFragment @Inject
constructor() : TVShowFragment() {

    override val sortType = HIGHEST_RATED
}