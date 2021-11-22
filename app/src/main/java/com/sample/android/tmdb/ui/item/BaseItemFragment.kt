package com.sample.android.tmdb.ui.item

import android.os.Bundle
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.ui.BaseFragment
import com.sample.android.tmdb.ui.MainActivity
import com.sample.android.tmdb.ui.NavType

abstract class BaseItemFragment<T : TmdbItem> : BaseFragment<T>() {

    protected abstract val sortType: SortType

    override val navType: NavType?
        get() = (activity as MainActivity).navType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebase.logEventScreenView("${navType!!.name.lowercase()}_${sortType.name.lowercase()}")
    }
}

enum class SortType {
    MOST_POPULAR,
    HIGHEST_RATED,
    UPCOMING
}