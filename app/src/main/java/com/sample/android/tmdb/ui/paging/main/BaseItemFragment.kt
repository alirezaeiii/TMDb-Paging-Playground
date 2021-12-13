package com.sample.android.tmdb.ui.paging.main

import com.sample.android.tmdb.ui.paging.BaseFragment
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.ui.feed.NavType

abstract class BaseItemFragment<T : TmdbItem> : BaseFragment<T>() {

    protected abstract val sortType: SortType

    override val navType: NavType
        get() = (activity as MainActivity).navType
}

enum class SortType {
    MOST_POPULAR,
    HIGHEST_RATED,
    UPCOMING
}