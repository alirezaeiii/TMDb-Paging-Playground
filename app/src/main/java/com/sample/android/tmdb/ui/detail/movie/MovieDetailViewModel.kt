package com.sample.android.tmdb.ui.detail.movie

import com.sample.android.tmdb.domain.CreditWrapper
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.domain.VideoWrapper
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.ui.detail.DetailViewModel
import io.reactivex.Observable

class MovieDetailViewModel(
        api: MovieApi,
        item: TmdbItem
) : DetailViewModel() {

    override val trailers: Observable<VideoWrapper> = api.movieTrailers(item.id)

    override val credits: Observable<CreditWrapper> = api.movieCredit(item.id)
}