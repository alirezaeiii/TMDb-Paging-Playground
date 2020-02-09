package com.sample.android.tmdb.ui.item.tvshow

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.ui.TmdbAdapter
import com.sample.android.tmdb.ui.detail.EXTRA_TV_SHOW
import com.sample.android.tmdb.ui.item.BaseItemFragment

abstract class TVShowFragment : BaseItemFragment<TVShow>() {

    override val viewModel by lazy { ViewModelProviders.of(this, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return TVShowsViewModel(useCase = useCase,
                    sortType = sortType) as T
        }
    })[TVShowsViewModel::class.java] }

    override fun getAdapter(retryCallback: () -> Unit): TmdbAdapter<TVShow> = TVShowAdapter(this, retryCallback)

    override val keyItem = EXTRA_TV_SHOW
}