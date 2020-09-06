package com.sample.android.tmdb.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sample.android.tmdb.domain.*
import com.sample.android.tmdb.ui.BaseViewModel
import io.reactivex.Observable
import timber.log.Timber

abstract class DetailViewModel(item: TmdbItem) : BaseViewModel() {

    private val _trailers = MutableLiveData<List<Video>>()
    val trailers: LiveData<List<Video>>
        get() = _trailers

    private val _cast: MutableLiveData<List<Cast>> by lazy {
        MutableLiveData<List<Cast>>().also {
            arrayOf(composeObservable { getTrailers(item.id).map { it.videos } }
                    .subscribe({ videos ->
                        _trailers.postValue(videos)
                    }) { throwable -> Timber.e(throwable) }
                    , composeObservable { getCast(item.id).map { it.cast } }
                    .subscribe({ cast ->
                        _cast.postValue(cast)
                    }) { throwable -> Timber.e(throwable) }).also { compositeDisposable.addAll(*it) }
        }
    }
    val cast: LiveData<List<Cast>>
        get() = _cast

    protected abstract fun getTrailers(id: Int): Observable<VideoWrapper>

    protected abstract fun getCast(id: Int): Observable<CastWrapper>
}