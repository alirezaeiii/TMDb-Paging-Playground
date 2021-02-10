package com.sample.android.tmdb.ui.search.tvshow

import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.ui.search.BaseSearchFragment
import javax.inject.Inject

class SearchTVShowFragment @Inject
constructor() // Required empty public constructor
    : BaseSearchFragment<TVShow>() {

    @Inject
    lateinit var factory: SearchTVShowViewModel.Factory

    override val viewModel by lazy { ViewModelProvider(this, factory).get(SearchTVShowViewModel::class.java) }
}