package com.sample.android.tmdb.ui.search

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.ui.BaseFragment
import com.sample.android.tmdb.ui.ItemAdapter
import com.sample.android.tmdb.ui.detail.EXTRA_TV_SHOW
import com.sample.android.tmdb.ui.tvshow.TVShowAdapter
import com.sample.android.tmdb.util.NavType
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class SearchTVShowFragment @Inject
constructor() // Required empty public constructor
    : BaseFragment<TVShow>() {

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return SearchTVShowViewModel(dataSource = dataSource) as T
            }
        })[SearchTVShowViewModel::class.java]
    }

    override fun getAdapter(retryCallback: () -> Unit): ItemAdapter<TVShow> = TVShowAdapter(this, retryCallback)

    override val keyParcelable = EXTRA_TV_SHOW

    override fun getNavType(): NavType = (activity as SearchActivity).navType

    fun search(query: String?) {
        if (viewModel.showQuery(query)) {
            list.scrollToPosition(0)
            (list.adapter as TVShowAdapter).submitList(null)
        }
    }
}