package com.sample.android.tmdb.ui.paging.main.tvshow

import com.sample.android.tmdb.domain.model.SortType
import javax.inject.Inject

class DiscoverTVShowsFragment @Inject
constructor() : TVShowPagingFragment() {

    override val sortType = SortType.DISCOVER
}