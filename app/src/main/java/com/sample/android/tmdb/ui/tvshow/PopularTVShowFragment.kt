package com.sample.android.tmdb.ui.tvshow

import com.sample.android.tmdb.util.SortType.MOST_POPULAR
import javax.inject.Inject

class PopularTVShowFragment @Inject
constructor() : MainTVShowFragment() {

    override val sortType = MOST_POPULAR
}