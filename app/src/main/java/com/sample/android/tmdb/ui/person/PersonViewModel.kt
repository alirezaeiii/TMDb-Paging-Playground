package com.sample.android.tmdb.ui.person

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import com.sample.android.tmdb.domain.Person
import com.sample.android.tmdb.ui.BaseViewModel
import com.sample.android.tmdb.usecase.UseCase
import timber.log.Timber

class PersonViewModel(useCase: UseCase, personId: Int) : BaseViewModel() {

    private val _person = MutableLiveData<Person>()
    val person: LiveData<Person>
        get() = _person

    val isPersonDetailVisible = ObservableBoolean(false)

    private val _knownAs = MutableLiveData<String>()
    val knownAs: LiveData<String>
        get() = _knownAs

    init {
        compositeDisposable.add(useCase.getPerson(personId)
                .subscribe({ person ->
                    isPersonDetailVisible.set(true)
                    _person.postValue(person)
                    var alsoKnownAs = ""
                    for (i in person.alsoKnowAs.indices) {
                        alsoKnownAs += person.alsoKnowAs[i]
                        if (i != person.alsoKnowAs.size - 1) {
                            alsoKnownAs += ", "
                        }
                    }
                    _knownAs.postValue(alsoKnownAs)
                }
                ) { throwable -> Timber.e(throwable) })
    }
}