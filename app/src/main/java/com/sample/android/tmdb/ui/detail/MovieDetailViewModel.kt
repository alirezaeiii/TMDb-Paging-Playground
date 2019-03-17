package com.sample.android.tmdb.ui.detail

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.util.EspressoIdlingResource
import com.sample.android.tmdb.vo.Cast
import com.sample.android.tmdb.vo.Movie
import com.sample.android.tmdb.vo.Video
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MovieDetailViewModel(
        context: Application,
        private val dataSource: MoviesRemoteDataSource)
    : AndroidViewModel(context) {

    val trailers: ObservableList<Video> = ObservableArrayList()
    val isTrailersVisible = ObservableBoolean(false)
    val cast = MutableLiveData<List<Cast>>()
    val isCastVisible = ObservableBoolean(false)

    fun showTrailers(movie: Movie): Disposable {
        EspressoIdlingResource.increment() // App is busy until further notice
        return dataSource.getTrailers(movie.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally {
                    if (!EspressoIdlingResource.getIdlingResource().isIdleNow) {
                        EspressoIdlingResource.decrement() // Set app as idle.
                    }
                }
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
    }

    fun showCast(movie: Movie): Disposable {
        EspressoIdlingResource.increment() // App is busy until further notice
        return dataSource.getCast(movie.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally {
                    if (!EspressoIdlingResource.getIdlingResource().isIdleNow) {
                        EspressoIdlingResource.decrement() // Set app as idle.
                    }
                }
                .subscribe({ cast ->
                    if (!cast.isEmpty()) {
                        isCastVisible.set(true)
                    }
                    this.cast.postValue(cast)
                }
                ) { throwable -> Timber.e(throwable) }
    }
}