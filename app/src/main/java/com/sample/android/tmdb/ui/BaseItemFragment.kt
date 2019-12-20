package com.sample.android.tmdb.ui

import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.util.SortType

abstract class BaseItemFragment<T : TmdbItem> : BaseFragment<T>() {

    protected abstract val sortType: SortType
}