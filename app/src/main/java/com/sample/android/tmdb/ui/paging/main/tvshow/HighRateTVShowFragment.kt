package com.sample.android.tmdb.ui.paging.main.tvshow

import com.sample.android.tmdb.ui.paging.main.SortType.HIGHEST_RATED
import javax.inject.Inject

class HighRateTVShowFragment @Inject
constructor() : TVShowFragment() {

    override val sortType = HIGHEST_RATED
}