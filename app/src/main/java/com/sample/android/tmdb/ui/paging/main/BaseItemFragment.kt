package com.sample.android.tmdb.ui.paging.main

import android.os.Parcelable
import com.sample.android.tmdb.data.TmdbItem
import com.sample.android.tmdb.ui.paging.BaseFragment
import kotlinx.android.parcel.Parcelize

abstract class BaseItemFragment<T : TmdbItem> : BaseFragment<T>() {

    protected abstract val sortType: SortType
}

@Parcelize
enum class SortType : Parcelable {
    TRENDING,
    MOST_POPULAR,
    HIGHEST_RATED,
    UPCOMING,
    NOW_PLAYING
}