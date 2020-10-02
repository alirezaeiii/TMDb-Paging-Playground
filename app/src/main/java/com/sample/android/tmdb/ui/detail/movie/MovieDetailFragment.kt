package com.sample.android.tmdb.ui.detail.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.ui.detail.DetailFragment
import javax.inject.Inject

class MovieDetailFragment @Inject
constructor() // Required empty public constructor
    : DetailFragment() {

    @Inject
    lateinit var api: MovieApi

    override val viewModel by lazy {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MovieDetailViewModel(api, item) as T
            }
        })[MovieDetailViewModel::class.java]
    }
}