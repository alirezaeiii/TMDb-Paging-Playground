package com.sample.android.tmdb.usecase

import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.domain.Cast
import com.sample.android.tmdb.domain.Video
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailUseCase @Inject constructor(
        private val itemApi: ItemApi) : BaseUseCase() {

    fun getMovieTrailers(id: Int): Observable<List<Video>> = composeObservable { itemApi.movieTrailers(id).map { it.videos } }

    fun getMovieCast(id: Int): Observable<List<Cast>> = composeObservable { itemApi.movieCast(id).map { it.cast } }

    fun getTvTrailers(id: Int): Observable<List<Video>> = composeObservable { itemApi.tvTrailers(id).map { it.videos } }

    fun getTvCast(id: Int): Observable<List<Cast>> = composeObservable { itemApi.tvCast(id).map { it.cast } }
}