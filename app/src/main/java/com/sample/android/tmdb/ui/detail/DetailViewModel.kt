package com.sample.android.tmdb.ui.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import com.sample.android.tmdb.domain.Cast
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.domain.Video
import com.sample.android.tmdb.ui.BaseViewModel
import com.sample.android.tmdb.util.EspressoIdlingResource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

abstract class DetailViewModel(private val item: TmdbItem) : BaseViewModel() {

    val trailers: ObservableList<Video> by lazy {
        ObservableArrayList<Video>().also {
            showTrailers()
        }
    }

    val isTrailersVisible = ObservableBoolean(false)
    val isCastVisible = ObservableBoolean(false)

    private val _cast: MutableLiveData<List<Cast>> by lazy {
        MutableLiveData<List<Cast>>().also {
            showCast()
        }
    }
    val cast: LiveData<List<Cast>>
        get() = _cast

    protected abstract fun getTrailers(id: Int): Observable<List<Video>>

    protected abstract fun getCast(id: Int): Observable<List<Cast>>

    private fun showTrailers() {
        EspressoIdlingResource.increment() // App is busy until further notice
        compositeDisposable.add(getTrailers(item.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally {
                    if (!EspressoIdlingResource.getIdlingResource().isIdleNow) {
                        EspressoIdlingResource.decrement() // Set app as idle.
                    }
                }
                .subscribe({ videos ->
                    if (videos.isNotEmpty()) {
                        isTrailersVisible.set(true)
                    }
                    with(trailers) {
                        clear()
                        addAll(videos)
                    }
                }
                ) { throwable -> Timber.e(throwable) })
    }

    private fun showCast() {
        EspressoIdlingResource.increment() // App is busy until further notice
        compositeDisposable.add(getCast(item.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally {
                    if (!EspressoIdlingResource.getIdlingResource().isIdleNow) {
                        EspressoIdlingResource.decrement() // Set app as idle.
                    }
                }
                .subscribe({ cast ->
                    if (cast.isNotEmpty()) {
                        isCastVisible.set(true)
                    }
                    _cast.postValue(cast)
                }
                ) { throwable -> Timber.e(throwable) })
    }
}