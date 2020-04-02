package com.sample.android.tmdb.ui.detail.movie

import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.network.TmdbApi
import com.sample.android.tmdb.ui.detail.DetailViewModel
import io.reactivex.Observable

class MovieDetailViewModel(private val api: TmdbApi,
                           item: TmdbItem) : DetailViewModel(item) {

    override fun getTrailers(id: Int): Observable<TmdbApi.VideoWrapper> = api.movieTrailers(id)

    override fun getCast(id: Int): Observable<TmdbApi.CastWrapper> = api.movieCast(id)
}