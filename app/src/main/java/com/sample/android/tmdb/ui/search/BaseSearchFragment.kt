package com.sample.android.tmdb.ui.search

import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.ui.BaseFragment
import com.sample.android.tmdb.ui.NavType
import com.sample.android.tmdb.ui.TmdbAdapter
import kotlinx.android.synthetic.main.fragment_main.*

abstract class BaseSearchFragment<T : TmdbItem> : BaseFragment<T>() {

    override val navType: NavType by lazy { (activity as SearchActivity).navType }

    fun search(query: String) {
        if ((viewModel as BaseSearchViewModel).showQuery(query)) {
            recyclerView.scrollToPosition(0)
            @Suppress("UNCHECKED_CAST")
            (recyclerView.adapter as TmdbAdapter<T>).submitList(null)
        }
    }
}