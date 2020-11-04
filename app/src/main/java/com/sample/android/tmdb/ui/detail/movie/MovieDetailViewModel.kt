package com.sample.android.tmdb.ui.detail.movie

import com.sample.android.tmdb.domain.CreditWrapper
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.domain.VideoWrapper
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.ui.detail.DetailViewModel
import io.reactivex.Observable

class MovieDetailViewModel(private val api: MovieApi,
                           private val item: TmdbItem) : DetailViewModel() {

    override fun getTrailers(): Observable<VideoWrapper> = api.movieTrailers(item.id)

    override fun getCredit(): Observable<CreditWrapper> = api.movieCredit(item.id)
}