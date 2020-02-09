package com.sample.android.tmdb.ui.detail.movie

import com.sample.android.tmdb.domain.Cast
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.domain.Video
import com.sample.android.tmdb.ui.detail.DetailViewModel
import com.sample.android.tmdb.usecase.UseCase
import io.reactivex.Observable

class MovieDetailViewModel(private val useCase: UseCase,
                           item: TmdbItem) : DetailViewModel(item) {

    override fun getTrailers(id: Int): Observable<List<Video>> = useCase.getMovieTrailers(id)

    override fun getCast(id: Int): Observable<List<Cast>> = useCase.getMovieCast(id)
}