package com.sample.android.tmdb.ui.feed

import androidx.fragment.app.viewModels
import com.sample.android.tmdb.domain.Movie

class FeedMovieFragment: FeedFragment<Movie>() {

    override val viewModel: FeedMovieViewModel by viewModels()
}