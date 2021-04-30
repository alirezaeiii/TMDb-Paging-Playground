package com.sample.android.tmdb.ui.item.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.ui.item.BaseItemFragment
import javax.inject.Inject

abstract class MovieFragment : BaseItemFragment<Movie>() {

    @Inject
    lateinit var api: MovieApi

    override val viewModel
        get() = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MovieViewModel(api, sortType, requireNotNull(activity).application) as T
            }
        })[MovieViewModel::class.java]
}