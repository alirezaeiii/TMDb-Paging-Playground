package com.sample.android.tmdb.ui.search

import android.os.Bundle
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.paging.Status.RUNNING
import com.sample.android.tmdb.ui.BaseFragment
import com.sample.android.tmdb.ui.NavType
import com.sample.android.tmdb.ui.TmdbAdapter
import com.sample.android.tmdb.util.Firebase.Companion.ANALYTICS_SEARCH_ACTION
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebase.logEventScreenView("${navType.name.lowercase()}_$ANALYTICS_SEARCH_ACTION")
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