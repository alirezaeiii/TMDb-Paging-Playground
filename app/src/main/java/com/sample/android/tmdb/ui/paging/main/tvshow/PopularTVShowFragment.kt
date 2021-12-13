package com.sample.android.tmdb.ui.paging.main.tvshow

import com.sample.android.tmdb.ui.paging.main.SortType.MOST_POPULAR
import javax.inject.Inject

class PopularTVShowFragment @Inject
constructor() : TVShowFragment() {

    override val sortType = MOST_POPULAR
}