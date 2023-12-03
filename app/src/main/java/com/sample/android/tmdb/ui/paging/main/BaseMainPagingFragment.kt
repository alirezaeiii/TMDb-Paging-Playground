package com.sample.android.tmdb.ui.paging.main

import com.sample.android.tmdb.domain.model.SortType
import com.sample.android.tmdb.domain.model.TmdbItem
import com.sample.android.tmdb.ui.paging.BasePagingFragment

abstract class BaseMainPagingFragment<T : TmdbItem> : BasePagingFragment<T>() {

    protected abstract val sortType: SortType
}