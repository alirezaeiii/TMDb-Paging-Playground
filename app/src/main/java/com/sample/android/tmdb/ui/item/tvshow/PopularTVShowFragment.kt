package com.sample.android.tmdb.ui.item.tvshow

import com.sample.android.tmdb.ui.item.SortType.MOST_POPULAR
import javax.inject.Inject

class PopularTVShowFragment @Inject
constructor() : TVShowFragment() {

    override val sortType = MOST_POPULAR
}