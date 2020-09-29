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

    private val _creditWrapper: MutableLiveData<CreditWrapper> by lazy {
        MutableLiveData<CreditWrapper>().also {
            arrayOf(composeObservable { getTrailers(item.id).map { it.videos } }
                    .subscribe({ videos ->
                        _trailers.postValue(videos)
                    }) { throwable -> Timber.e(throwable) }, composeObservable { getCredit(item.id) }
                    .subscribe({ credit ->
                        _creditWrapper.postValue(credit)
                    }) { throwable -> Timber.e(throwable) }).also { compositeDisposable.addAll(*it) }
        }
    }
    val creditWrapper: LiveData<CreditWrapper>
        get() = _creditWrapper

    protected abstract fun getTrailers(id: Int): Observable<VideoWrapper>

    protected abstract fun getCredit(id: Int): Observable<CreditWrapper>
}