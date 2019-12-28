package com.sample.android.tmdb.ui.item

import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.ui.BaseFragment
import com.sample.android.tmdb.ui.MainActivity
import com.sample.android.tmdb.util.NavType
import com.sample.android.tmdb.util.SortType

abstract class BaseItemFragment<T : TmdbItem> : BaseFragment<T>() {

    protected abstract val sortType: SortType

    override fun getNavType(): NavType? = (activity as MainActivity).getNavType()
}