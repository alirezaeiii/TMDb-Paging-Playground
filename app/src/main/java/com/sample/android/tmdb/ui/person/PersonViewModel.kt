package com.sample.android.tmdb.ui.person

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.sample.android.tmdb.network.TmdbApi
import com.sample.android.tmdb.domain.Person
import com.sample.android.tmdb.ui.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class PersonViewModel(api: TmdbApi, personId: Int) : BaseViewModel() {

    private val _person = MutableLiveData<Person>()
    val person: LiveData<Person>
        get() = _person

    private val _knownAs = MutableLiveData<String>()
    val knownAs: LiveData<String>
        get() = _knownAs

    init {
        composeObservable { api.person(personId) }
                .subscribe({ person ->
                    _person.postValue(person)
                    _knownAs.postValue(person.alsoKnowAs.joinToString())
                }
                ) { throwable -> Timber.e(throwable) }.also { compositeDisposable.add(it) }
    }

    /**
     * Factory for constructing PersonViewModel with parameter
     */
    class Factory @Inject constructor(
            private val api: TmdbApi,
            val person: PersonWrapper
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PersonViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PersonViewModel(api, person.personId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}