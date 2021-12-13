package com.sample.android.tmdb.ui.paging.search

import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.paging.Status.RUNNING
import com.sample.android.tmdb.ui.paging.BaseFragment
import com.sample.android.tmdb.ui.feed.NavType
import com.sample.android.tmdb.ui.paging.TmdbAdapter
import com.sample.android.tmdb.util.toVisibility
import kotlinx.android.synthetic.main.fragment_main.*

abstract class BaseSearchFragment<T : TmdbItem> : BaseFragment<T>() {

    private val searchViewModel
        get() = viewModel as BaseSearchViewModel

    override val navType: NavType
        get() = (activity as SearchActivity).navType

    override fun refresh() {
        super.refresh()
        searchViewModel.refreshState.removeObservers(this)
    }

    fun search(query: String) {
        if (searchViewModel.showQuery(query)) {
            recyclerView.scrollToPosition(0)
            @Suppress("UNCHECKED_CAST")
            (recyclerView.adapter as TmdbAdapter<T>).submitList(null)
        }
    }

    fun observeRefreshState() {
        searchViewModel.refreshState.observe(this, {
            recyclerView.toVisibility(it.status != RUNNING)
        })
    }
}