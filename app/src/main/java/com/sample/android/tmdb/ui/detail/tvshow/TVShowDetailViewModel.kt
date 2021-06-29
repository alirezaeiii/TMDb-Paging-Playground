package com.sample.android.tmdb.ui.detail.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.ui.detail.DetailViewModel
import javax.inject.Inject

class TVShowDetailViewModel(
        api: TVShowApi,
        item: TmdbItem
) : DetailViewModel(api.tvTrailers(item.id), api.tvCredit(item.id)) {

    init {
        sendRequest()
    }

    class Factory @Inject constructor(
            private val api: TVShowApi,
            private val item: TmdbItem
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TVShowDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return TVShowDetailViewModel(api, item) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}