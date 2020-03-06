package com.sample.android.tmdb.ui.search

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.ui.TmdbAdapter
import com.sample.android.tmdb.ui.detail.EXTRA_MOVIE
import com.sample.android.tmdb.ui.item.movie.MovieAdapter
import javax.inject.Inject

class SearchMovieFragment @Inject
constructor() // Required empty public constructor
    : BaseSearchFragment<Movie>() {

    override val viewModel by lazy { ViewModelProviders.of(this, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return SearchMovieViewModel(api) as T
        }
    })[SearchMovieViewModel::class.java] }

    override fun getAdapter(retryCallback: () -> Unit): TmdbAdapter<Movie> = MovieAdapter(this, retryCallback)

    override val keyItem = EXTRA_MOVIE
}