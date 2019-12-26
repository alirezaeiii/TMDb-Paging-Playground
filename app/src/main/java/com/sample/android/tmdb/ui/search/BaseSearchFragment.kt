package com.sample.android.tmdb.ui.search

import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*

abstract class BaseSearchFragment<T : TmdbItem> : BaseFragment<T>() {

    protected abstract fun resetAdapter()

    internal fun search(query: String) {
        if (viewModel.showQuery(query)) {
            list.scrollToPosition(0)
            resetAdapter()
        }
    }
}