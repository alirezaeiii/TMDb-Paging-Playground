package com.sample.android.tmdb.ui.feed

import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.repository.MovieRepository

class FeedMovieViewModel(repository: MovieRepository) :
    FeedViewModel<Movie>(repository)