package com.sample.android.tmdb.ui.paging.main

import com.sample.android.tmdb.domain.model.SortType
import com.sample.android.tmdb.domain.model.TmdbItem
import com.sample.android.tmdb.ui.paging.BaseFragment

abstract class BaseItemFragment<T : TmdbItem> : BaseFragment<T>() {

    protected abstract val sortType: SortType
}