package com.sample.android.tmdb.ui.detail.movie

import com.sample.android.tmdb.repository.RemoteDataSource
import com.sample.android.tmdb.ui.detail.DetailViewModel
import com.sample.android.tmdb.domain.Cast
import com.sample.android.tmdb.domain.Video
import io.reactivex.Observable

class MovieDetailViewModel(private val dataSource: RemoteDataSource)
    : DetailViewModel() {

    override fun getTrailers(id: Int): Observable<List<Video>> = dataSource.getMovieTrailers(id)

    override fun getCast(id: Int): Observable<List<Cast>> = dataSource.getMovieCast(id)
}