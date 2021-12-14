package com.sample.android.tmdb.ui.feed

import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.repository.TmdbRepository

class FeedMovieViewModel(repository: TmdbRepository<Movie>) :
    FeedViewModel<Movie>(repository)