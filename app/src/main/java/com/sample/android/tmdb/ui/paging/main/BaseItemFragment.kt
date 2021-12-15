package com.sample.android.tmdb.ui.paging.main

import android.os.Parcelable
import com.sample.android.tmdb.ui.paging.BaseFragment
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.ui.feed.NavType
import kotlinx.android.parcel.Parcelize

abstract class BaseItemFragment<T : TmdbItem> : BaseFragment<T>() {

    protected abstract val sortType: SortType

    override val navType: NavType
        get() = (activity as MainActivity).navType
}

@Parcelize
enum class SortType : Parcelable {
    MOST_POPULAR,
    HIGHEST_RATED,
    UPCOMING
}