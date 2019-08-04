package com.sample.android.tmdb.ui.detail.tvshow

import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.ui.detail.DetailViewModel
import com.sample.android.tmdb.vo.Cast
import com.sample.android.tmdb.vo.TVShow
import com.sample.android.tmdb.vo.Video
import io.reactivex.Observable

class TVShowDetailViewModel(private val dataSource: MoviesRemoteDataSource)
    : DetailViewModel<TVShow>() {

    override fun getTrailers(id: Int): Observable<List<Video>> = dataSource.getTvTrailers(id)

    override fun getCast(id: Int): Observable<List<Cast>> = dataSource.getTvCast(id)
}