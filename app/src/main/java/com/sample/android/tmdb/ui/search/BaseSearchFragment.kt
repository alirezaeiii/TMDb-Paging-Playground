package com.sample.android.tmdb.ui.search

import android.os.Bundle
import androidx.lifecycle.Observer
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.paging.Status.RUNNING
import com.sample.android.tmdb.ui.BaseFragment
import com.sample.android.tmdb.ui.NavType
import com.sample.android.tmdb.ui.TmdbAdapter
import com.sample.android.tmdb.util.toVisibility
import kotlinx.android.synthetic.main.fragment_main.*

abstract class BaseSearchFragment<T : TmdbItem> : BaseFragment<T>() {

    override val navType: NavType by lazy { (activity as SearchActivity).navType }

    private val searchViewModel by lazy { viewModel as BaseSearchViewModel }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        swipe_refresh.setOnRefreshListener {
            searchViewModel.refreshState.removeObservers(this)
            searchViewModel.refresh()
        }
    }

    fun search(query: String) {
        if (searchViewModel.showQuery(query)) {
            searchViewModel.refreshState.observe(this, Observer {
                recyclerView.toVisibility(it.status != RUNNING)
            })
            recyclerView.scrollToPosition(0)
            @Suppress("UNCHECKED_CAST")
            (recyclerView.adapter as TmdbAdapter<T>).submitList(null)
        }
    }
}