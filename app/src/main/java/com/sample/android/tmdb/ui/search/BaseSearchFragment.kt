package com.sample.android.tmdb.ui.search

import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.ui.BaseFragment
import com.sample.android.tmdb.ui.TmdbAdapter
import com.sample.android.tmdb.util.NavType
import kotlinx.android.synthetic.main.fragment_main.*

abstract class BaseSearchFragment<T : TmdbItem> : BaseFragment<T>() {

    override fun getNavType(): NavType = (activity as SearchActivity).navType

    internal fun search(query: String) {
        if (viewModel.showQuery(query)) {
            list.scrollToPosition(0)
            @Suppress("UNCHECKED_CAST")
            (list.adapter as TmdbAdapter<T>).submitList(null)
        }
    }
}