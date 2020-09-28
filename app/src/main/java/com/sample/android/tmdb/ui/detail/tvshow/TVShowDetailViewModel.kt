package com.sample.android.tmdb.ui.detail.tvshow

import com.sample.android.tmdb.domain.CreditWrapper
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.domain.VideoWrapper
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.ui.detail.DetailViewModel
import io.reactivex.Observable

class TVShowDetailViewModel(private val api: TVShowApi,
                            item: TmdbItem) : DetailViewModel(item) {

    override fun getTrailers(id: Int): Observable<VideoWrapper> = api.tvTrailers(id)

    override fun getCast(id: Int): Observable<CreditWrapper> = api.tvCast(id)
}