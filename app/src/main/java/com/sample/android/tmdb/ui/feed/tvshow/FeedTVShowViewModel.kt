package com.sample.android.tmdb.ui.feed.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.repository.TVShowRepository
import com.sample.android.tmdb.ui.feed.FeedViewModel
import javax.inject.Inject

class FeedTVShowViewModel(repository: TVShowRepository) :
    FeedViewModel<TVShow>(repository) {

    class Factory @Inject constructor(
        private val repository: TVShowRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FeedTVShowViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FeedTVShowViewModel(repository) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}