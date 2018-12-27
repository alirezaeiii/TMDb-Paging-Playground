package com.sample.android.tmdb.ui.person

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.vo.Person
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class PersonViewModel(
        context: Application,
        private val dataSource: MoviesRemoteDataSource)
    : AndroidViewModel(context) {

    internal val compositeDisposable = CompositeDisposable()
    val person = MutableLiveData<Person>()
    val isVisible = ObservableBoolean(false)

    fun showPerson(personId: Int) {
        val personSubscription = dataSource.getPerson(personId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ person ->
                    isVisible.set(true)
                    this.person.value = person
                }
                ) { throwable -> Timber.e(throwable) }

        compositeDisposable.add(personSubscription)
    }
}