package com.sample.android.tmdb.ui.detail.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.domain.CreditWrapper
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.domain.VideoWrapper
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.ui.detail.DetailViewModel
import io.reactivex.Observable
import javax.inject.Inject

class TVShowDetailViewModel(
        api: TVShowApi,
        item: TmdbItem
) : DetailViewModel() {

    override val trailers: Observable<VideoWrapper> = api.tvTrailers(item.id)

    override val credits: Observable<CreditWrapper> = api.tvCredit(item.id)

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