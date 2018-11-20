package com.sample.android.tmdb.ui.base

import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.SortType.MOST_POPULAR
import com.sample.android.tmdb.di.ActivityScoped
import com.sample.android.tmdb.ui.base.BaseFragment
import javax.inject.Inject

@ActivityScoped
class PopularMoviesFragment @Inject
constructor() : BaseFragment() {

    override fun getSortType(): SortType = MOST_POPULAR
}