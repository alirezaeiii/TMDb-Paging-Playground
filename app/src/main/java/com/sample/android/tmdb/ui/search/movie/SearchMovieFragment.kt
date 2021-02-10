package com.sample.android.tmdb.ui.search.movie

import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.ui.search.BaseSearchFragment
import javax.inject.Inject

class SearchMovieFragment @Inject
constructor() // Required empty public constructor
    : BaseSearchFragment<Movie>() {

    @Inject
    lateinit var factory: SearchMovieViewModel.Factory

    override val viewModel by lazy { ViewModelProvider(this, factory).get(SearchMovieViewModel::class.java) }
}