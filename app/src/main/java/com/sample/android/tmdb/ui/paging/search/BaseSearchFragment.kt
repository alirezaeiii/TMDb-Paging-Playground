package com.sample.android.tmdb.ui.paging.search

import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.paging.Status.RUNNING
import com.sample.android.tmdb.ui.paging.BaseFragment
import com.sample.android.tmdb.ui.paging.TmdbAdapter
import com.sample.android.tmdb.util.toVisibility

abstract class BaseSearchFragment<T : TmdbItem> : BaseFragment<T>() {

    private val searchViewModel
        get() = viewModel as BaseSearchViewModel

    override fun refresh() {
        super.refresh()
        searchViewModel.refreshState.removeObservers(this)
    }

    fun search(query: String) {
        if (searchViewModel.showQuery(query)) {
            binding.recyclerView.scrollToPosition(0)
            @Suppress("UNCHECKED_CAST")
            (binding.recyclerView.adapter as TmdbAdapter<T>).submitList(null)
        }
    }

    fun observeRefreshState() {
        searchViewModel.refreshState.observe(this) {
            binding.recyclerView.toVisibility(it.status != RUNNING)
        }
    }
}