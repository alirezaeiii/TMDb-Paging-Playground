package com.sample.android.tmdb.ui.paging.main.tvshow

import com.sample.android.tmdb.domain.model.SortType.UPCOMING
import javax.inject.Inject

class OnTheAirTVShowFragment @Inject
constructor() : TVShowPagingFragment() {

    override val sortType = UPCOMING
}