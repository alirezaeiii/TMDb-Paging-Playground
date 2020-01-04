package com.sample.android.tmdb.ui.search

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.ui.TmdbAdapter
import com.sample.android.tmdb.ui.detail.EXTRA_TV_SHOW
import com.sample.android.tmdb.ui.item.tvshow.TVShowAdapter
import javax.inject.Inject

class SearchTVShowFragment @Inject
constructor() // Required empty public constructor
    : BaseSearchFragment<TVShow>() {

    override fun getTmdbViewModel() = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return SearchTVShowViewModel(dataSource = dataSource) as T
        }
    })[SearchTVShowViewModel::class.java]

    override fun getAdapter(retryCallback: () -> Unit): TmdbAdapter<TVShow> = TVShowAdapter(this, retryCallback)

    override val keyParcelable = EXTRA_TV_SHOW
}