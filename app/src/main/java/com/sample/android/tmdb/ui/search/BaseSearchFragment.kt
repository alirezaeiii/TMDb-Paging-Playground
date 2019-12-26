package com.sample.android.tmdb.ui.search

import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.ui.BaseFragment
import com.sample.android.tmdb.ui.ItemAdapter
import kotlinx.android.synthetic.main.fragment_main.*

abstract class BaseSearchFragment<T : TmdbItem> : BaseFragment<T>() {

    internal fun search(query: String) {
        if (viewModel.showQuery(query)) {
            list.scrollToPosition(0)
            @Suppress("UNCHECKED_CAST")
            (list.adapter as ItemAdapter<T>).submitList(null)
        }
    }
}