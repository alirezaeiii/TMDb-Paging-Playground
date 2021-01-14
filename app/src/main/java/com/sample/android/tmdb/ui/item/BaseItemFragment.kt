package com.sample.android.tmdb.ui.item

import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.ui.BaseFragment
import com.sample.android.tmdb.ui.MainActivity
import com.sample.android.tmdb.ui.NavType

abstract class BaseItemFragment<T : TmdbItem> : BaseFragment<T>() {

    protected abstract val sortType: SortType

    override val navType: NavType? by lazy { (activity as MainActivity).getNavType() }
}

enum class SortType {
    MOST_POPULAR,
    HIGHEST_RATED,
    UPCOMING
}