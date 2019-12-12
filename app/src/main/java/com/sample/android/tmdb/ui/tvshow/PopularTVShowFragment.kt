package com.sample.android.tmdb.ui.tvshow

import com.sample.android.tmdb.di.ActivityScoped
import com.sample.android.tmdb.util.SortType.MOST_POPULAR
import javax.inject.Inject

@ActivityScoped
class PopularTVShowFragment @Inject
constructor() : TVShowFragment() {

    override val sortType = MOST_POPULAR
}