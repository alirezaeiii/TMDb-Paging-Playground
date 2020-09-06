package com.sample.android.tmdb.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.ui.TmdbAdapter
import com.sample.android.tmdb.ui.detail.EXTRA_TV_SHOW
import com.sample.android.tmdb.ui.item.tvshow.TVShowAdapter
import javax.inject.Inject

class SearchTVShowFragment @Inject
constructor() // Required empty public constructor
    : BaseSearchFragment<TVShow>() {

    @Inject
    lateinit var api: TVShowApi

    override val viewModel by lazy { ViewModelProviders.of(this, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return SearchTVShowViewModel(api, requireNotNull(activity).application) as T
        }
    })[SearchTVShowViewModel::class.java] }

    override fun getAdapter(retryCallback: () -> Unit): TmdbAdapter<TVShow> = TVShowAdapter(this, retryCallback)

    override val keyItem = EXTRA_TV_SHOW
}