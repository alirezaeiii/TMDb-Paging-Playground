package com.sample.android.tmdb.ui.detail.movie

import com.sample.android.tmdb.domain.CreditWrapper
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.domain.VideoWrapper
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.ui.detail.DetailViewModel
import io.reactivex.Observable

class MovieDetailViewModel(private val api: MovieApi,
                           item: TmdbItem) : DetailViewModel(item) {

    override fun getTrailers(id: Int): Observable<VideoWrapper> = api.movieTrailers(id)

    override fun getCast(id: Int): Observable<CreditWrapper> = api.movieCast(id)
}