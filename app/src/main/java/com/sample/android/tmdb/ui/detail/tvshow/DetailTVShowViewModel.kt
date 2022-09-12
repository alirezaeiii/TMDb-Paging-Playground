package com.sample.android.tmdb.ui.detail.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.domain.repo.TVShowDetailRepository
import com.sample.android.tmdb.ui.detail.DetailViewModel
import javax.inject.Inject

class DetailTVShowViewModel(
    repository: TVShowDetailRepository,
    item: TmdbItem
) : DetailViewModel(
    repository.getTVShowTrailers(item.id),
    repository.getTVShowCredit(item.id)
) {

    class Factory @Inject constructor(
        private val repository: TVShowDetailRepository,
        private val item: TmdbItem
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailTVShowViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DetailTVShowViewModel(repository, item) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}