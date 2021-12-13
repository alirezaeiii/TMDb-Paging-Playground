package com.sample.android.tmdb.ui.feed

import androidx.fragment.app.viewModels
import com.sample.android.tmdb.domain.Movie
import javax.inject.Inject

class FeedMovieFragment @Inject
constructor() // Required empty public constructor
    : FeedFragment<Movie>() {

    override val viewModel: FeedMovieViewModel by viewModels()
}