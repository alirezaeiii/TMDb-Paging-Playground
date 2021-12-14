package com.sample.android.tmdb.ui.feed

import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.domain.Movie
import javax.inject.Inject

class FeedMovieFragment @Inject
constructor() // Required empty public constructor
    : FeedFragment<Movie>() {

    @Inject
    lateinit var factory: FeedMovieViewModel.Factory

    override val viewModel
        get() = ViewModelProvider(this, factory).get(FeedMovieViewModel::class.java)

    override val navType: NavType
        get() = NavType.MOVIES
}