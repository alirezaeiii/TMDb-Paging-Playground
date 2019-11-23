package com.sample.android.tmdb.ui.tvshow

import com.sample.android.tmdb.util.SortType
import com.sample.android.tmdb.util.SortType.MOST_POPULAR
import com.sample.android.tmdb.di.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PopularTVShowFragment @Inject
constructor() : TVShowFragment() {

    override fun getSortType(): SortType = MOST_POPULAR
}