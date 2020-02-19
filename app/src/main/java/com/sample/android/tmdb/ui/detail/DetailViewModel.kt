package com.sample.android.tmdb.ui.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.sample.android.tmdb.domain.Cast
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.domain.Video
import com.sample.android.tmdb.ui.BaseViewModel
import io.reactivex.Observable
import timber.log.Timber

abstract class DetailViewModel(item: TmdbItem) : BaseViewModel() {

    private val _trailers = MutableLiveData<List<Video>>()
    val trailers: LiveData<List<Video>>
        get() = _trailers

    private val _cast: MutableLiveData<List<Cast>> by lazy {
        MutableLiveData<List<Cast>>().also {
            compositeDisposable.addAll(getTrailers(item.id)
                    .subscribe({ videos ->
                        _trailers.postValue(videos)
                    }) { throwable -> Timber.e(throwable) }
                    , getCast(item.id)
                    .subscribe({ cast ->
                        _cast.postValue(cast)
                    }) { throwable -> Timber.e(throwable) })
        }
    }
    val cast: LiveData<List<Cast>>
        get() = _cast

    protected abstract fun getTrailers(id: Int): Observable<List<Video>>

    protected abstract fun getCast(id: Int): Observable<List<Cast>>
}