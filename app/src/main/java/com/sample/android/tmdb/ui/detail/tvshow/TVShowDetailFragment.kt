package com.sample.android.tmdb.ui.detail.tvshow

import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.ui.detail.DetailFragment
import javax.inject.Inject

class TVShowDetailFragment @Inject
constructor() // Required empty public constructor
    : DetailFragment() {

    @Inject
    lateinit var factory: TVShowDetailViewModel.Factory

    override val viewModel by lazy { ViewModelProvider(this, factory).get(TVShowDetailViewModel::class.java) }
}