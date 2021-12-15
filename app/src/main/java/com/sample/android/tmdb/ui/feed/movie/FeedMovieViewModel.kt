package com.sample.android.tmdb.ui.feed.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.repository.TmdbRepository
import com.sample.android.tmdb.ui.feed.FeedViewModel
import javax.inject.Inject

class FeedMovieViewModel(repository: TmdbRepository<Movie>) :
    FeedViewModel<Movie>(repository) {

    class Factory @Inject constructor(
        private val repository: TmdbRepository<Movie>
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FeedMovieViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FeedMovieViewModel(repository) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}