package com.sample.android.tmdb.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.ui.detail.DetailActivity.Companion.EXTRA_TV_SHOW
import com.sample.android.tmdb.ui.tvshow.TVShowAdapter
import com.sample.android.tmdb.ui.tvshow.TVShowsViewModel

abstract class TVShowFragment : BaseFragment<TVShow>() {

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return TVShowsViewModel(dataSource = dataSource,
                        sortType = sortType) as T
            }
        })[TVShowsViewModel::class.java]
    }

    override fun getAdapter(retryCallback: () -> Unit): ItemAdapter<TVShow> = TVShowAdapter(this, retryCallback)

    override val keyParcelable = EXTRA_TV_SHOW
}