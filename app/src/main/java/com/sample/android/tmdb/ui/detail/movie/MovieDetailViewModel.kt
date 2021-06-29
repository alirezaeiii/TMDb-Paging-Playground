package com.sample.android.tmdb.ui.detail.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.ui.detail.DetailViewModel
import javax.inject.Inject

class MovieDetailViewModel(
        api: MovieApi,
        item: TmdbItem
) : DetailViewModel(api.movieTrailers(item.id), api.movieCredit(item.id)) {

    init {
        sendRequest()
    }

    class Factory @Inject constructor(
            private val api : MovieApi,
            private val item: TmdbItem
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MovieDetailViewModel(api, item) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}