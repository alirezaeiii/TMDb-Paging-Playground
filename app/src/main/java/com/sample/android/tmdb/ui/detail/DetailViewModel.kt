package com.sample.android.tmdb.ui.detail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import com.sample.android.tmdb.util.EspressoIdlingResource
import com.sample.android.tmdb.vo.Cast
import com.sample.android.tmdb.vo.TmdbItem
import com.sample.android.tmdb.vo.Video
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

abstract class DetailViewModel<T : TmdbItem> : ViewModel() {

    val trailers: ObservableList<Video> = ObservableArrayList()
    val isTrailersVisible = ObservableBoolean(false)
    val cast = MutableLiveData<List<Cast>>()
    val isCastVisible = ObservableBoolean(false)

    protected abstract fun getTrailers(id: Int): Observable<List<Video>>

    protected abstract fun getCast(id: Int): Observable<List<Cast>>

    fun showTrailers(t: T): Disposable {
        EspressoIdlingResource.increment() // App is busy until further notice
        return getTrailers(t.id)
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

    fun showCast(t: T): Disposable {
        EspressoIdlingResource.increment() // App is busy until further notice
        return getCast(t.id)
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