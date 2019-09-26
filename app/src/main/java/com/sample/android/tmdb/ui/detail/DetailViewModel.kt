package com.sample.android.tmdb.ui.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import com.sample.android.tmdb.ui.BaseViewModel
import com.sample.android.tmdb.util.EspressoIdlingResource
import com.sample.android.tmdb.vo.Cast
import com.sample.android.tmdb.vo.TmdbItem
import com.sample.android.tmdb.vo.Video
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

abstract class DetailViewModel : BaseViewModel() {

    val trailers: ObservableList<Video> = ObservableArrayList()
    val isTrailersVisible = ObservableBoolean(false)

    private val _cast = MutableLiveData<List<Cast>>()
    val cast: LiveData<List<Cast>> = _cast

    val isCastVisible = ObservableBoolean(false)

    protected abstract fun getTrailers(id: Int): Observable<List<Video>>

    protected abstract fun getCast(id: Int): Observable<List<Cast>>

    fun showTrailers(item: TmdbItem) {
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

    fun showCast(item: TmdbItem) {
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
                    this._cast.postValue(cast)
                }
                ) { throwable -> Timber.e(throwable) })
    }
}