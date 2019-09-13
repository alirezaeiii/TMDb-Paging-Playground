package com.sample.android.tmdb.ui.person

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.util.EspressoIdlingResource
import com.sample.android.tmdb.vo.Person
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class PersonViewModel(
        private val dataSource: MoviesRemoteDataSource)
    : ViewModel() {

    val person = MutableLiveData<Person>()
    val isVisible = ObservableBoolean(false)
    val knownAs = MutableLiveData<String>()

    fun showPerson(personId: Int): Disposable {
        EspressoIdlingResource.increment() // App is busy until further notice
        return dataSource.getPerson(personId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally {
                    if (!EspressoIdlingResource.getIdlingResource().isIdleNow) {
                        EspressoIdlingResource.decrement() // Set app as idle.
                    }
                }
                .subscribe({ person ->
                    isVisible.set(true)
                    this.person.postValue(person)
                    var alsoKnownAs = ""
                    for (i in person.alsoKnowAs.indices) {
                        alsoKnownAs += person.alsoKnowAs[i]
                        if (i != person.alsoKnowAs.size - 1) {
                            alsoKnownAs += ", "
                        }
                    }
                    knownAs.postValue(alsoKnownAs)
                }
                ) { throwable -> Timber.e(throwable) }
    }
}