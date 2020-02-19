package com.sample.android.tmdb.ui.person

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.sample.android.tmdb.domain.Person
import com.sample.android.tmdb.ui.BaseViewModel
import com.sample.android.tmdb.usecase.PersonUseCase
import timber.log.Timber
import javax.inject.Inject

class PersonViewModel(useCase: PersonUseCase, personId: Int) : BaseViewModel() {

    private val _person = MutableLiveData<Person>()
    val person: LiveData<Person>
        get() = _person

    private val _knownAs = MutableLiveData<String>()
    val knownAs: LiveData<String>
        get() = _knownAs

    init {
        compositeDisposable.add(useCase.getPerson(personId)
                .subscribe({ person ->
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

    /**
     * Factory for constructing PersonViewModel with parameter
     */
    class Factory @Inject constructor(
            private val useCase: PersonUseCase,
            val person: PersonExtra
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PersonViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PersonViewModel(useCase, person.personId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}