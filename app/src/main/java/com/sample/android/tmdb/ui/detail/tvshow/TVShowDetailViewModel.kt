package com.sample.android.tmdb.ui.detail.tvshow

import com.sample.android.tmdb.domain.CreditWrapper
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.domain.VideoWrapper
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.ui.detail.DetailViewModel
import io.reactivex.Observable

class TVShowDetailViewModel(private val api: TVShowApi,
                            private val item: TmdbItem) : DetailViewModel() {

    override fun getTrailers(): Observable<VideoWrapper> = api.tvTrailers(item.id)

    override fun getCredit(): Observable<CreditWrapper> = api.tvCredit(item.id)
}