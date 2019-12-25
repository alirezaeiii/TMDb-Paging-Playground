package com.sample.android.tmdb.ui.search

import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.ui.BaseFragment

abstract class BaseSearchFragment<T : TmdbItem> : BaseFragment<T>() {

    internal abstract fun search(query: String)
}