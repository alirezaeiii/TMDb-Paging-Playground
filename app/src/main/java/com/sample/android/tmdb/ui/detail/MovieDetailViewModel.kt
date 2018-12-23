package com.sample.android.tmdb.ui.detail

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.vo.Movie
import com.sample.android.tmdb.vo.Video
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MovieDetailViewModel(
        context: Application,
        private val dataSource: MoviesRemoteDataSource)
    : AndroidViewModel(context) {

    internal val compositeDisposable = CompositeDisposable()
    val trailers: ObservableList<Video> = ObservableArrayList()
    var isTrailersVisible = ObservableBoolean(false)

    fun showTrailers(movie: Movie) {
        val trailersSubscription = dataSource.getTrailers(movie.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ videos ->
                    if (!videos.isEmpty()) {
                        isTrailersVisible.set(true)
                    }
                    with(trailers) {
                        clear()
                        addAll(videos)
                    }
                }
                ) { throwable -> Timber.e(throwable) }
        compositeDisposable.add(trailersSubscription)
    }
}