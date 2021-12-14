package com.sample.android.tmdb.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.repository.TmdbRepository
import javax.inject.Inject

class FeedTVShowViewModel(repository: TmdbRepository<TVShow>) :
    FeedViewModel<TVShow>(repository) {

    class Factory @Inject constructor(
        private val repository: TmdbRepository<TVShow>
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