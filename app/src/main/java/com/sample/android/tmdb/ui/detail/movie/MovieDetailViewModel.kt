package com.sample.android.tmdb.ui.detail.movie

import com.sample.android.tmdb.network.TmdbApi
import com.sample.android.tmdb.domain.Cast
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.domain.Video
import com.sample.android.tmdb.ui.detail.DetailViewModel
import io.reactivex.Observable

class MovieDetailViewModel(private val api: TmdbApi,
                           item: TmdbItem) : DetailViewModel(item) {

    override fun getTrailers(id: Int): Observable<List<Video>> = api.movieTrailers(id).map { it.videos }

    override fun getCast(id: Int): Observable<List<Cast>> = api.movieCast(id).map { it.cast }
}