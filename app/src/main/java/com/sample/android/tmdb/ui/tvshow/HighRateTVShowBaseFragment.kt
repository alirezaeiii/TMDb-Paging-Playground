package com.sample.android.tmdb.ui.tvshow

import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.SortType.HIGHEST_RATED
import com.sample.android.tmdb.di.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class HighRateTVShowBaseFragment @Inject
constructor() : TVShowBaseFragment() {

    override fun getSortType(): SortType = HIGHEST_RATED
}