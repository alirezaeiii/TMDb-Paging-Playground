package com.sample.android.tmdb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.android.tmdb.util.DisposableManager
import com.sample.android.tmdb.util.EspressoIdlingResource
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

open class BaseDetailViewModel<T>(private val requestObservable: Single<T>) : ViewModel() {

    private val _liveData = MutableLiveData<T>()
    val liveData: LiveData<T>
        get() = _liveData

    protected fun sendRequest() {
        composeSingle { requestObservable }.subscribe({
            _liveData.postValue(it)
        }) {
            Timber.e(it)
        }.also { DisposableManager.add(it) }
    }

    private inline fun <T> composeSingle(task: () -> Single<T>): Single<T> = task()
            .doOnSubscribe { EspressoIdlingResource.increment() } // App is busy until further notice
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                if (!EspressoIdlingResource.getIdlingResource().isIdleNow) {
                    EspressoIdlingResource.decrement() // Set app as idle.
                }
            }

    /**
     * Called when the ViewModel is dismantled.
     * At this point, we want to cancel all disposables;
     * otherwise we end up with processes that have nowhere to return to
     * using memory and resources.
     */
    override fun onCleared() {
        super.onCleared()
        DisposableManager.clear()
    }
}