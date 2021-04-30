package com.sample.android.tmdb.ui.detail.movie

import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.ui.detail.DetailFragment
import javax.inject.Inject

class MovieDetailFragment @Inject
constructor() // Required empty public constructor
    : DetailFragment() {

    @Inject
    lateinit var factory: MovieDetailViewModel.Factory

    override val viewModel
        get() = ViewModelProvider(this, factory).get(MovieDetailViewModel::class.java)
}