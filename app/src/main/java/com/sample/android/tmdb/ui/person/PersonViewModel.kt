package com.sample.android.tmdb.ui.person

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.domain.Person
import com.sample.android.tmdb.network.PersonApi
import com.sample.android.tmdb.ui.BaseDetailViewModel
import javax.inject.Inject

class PersonViewModel(
        api: PersonApi,
        personId: Any
) : BaseDetailViewModel<Person>(api.getPerson(personId)) {

    init {
        sendRequest()
    }

    /**
     * Factory for constructing PersonViewModel with parameter
     */
    class Factory @Inject constructor(
            private val api: PersonApi,
            private val person: PersonWrapper
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PersonViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PersonViewModel(api, person.credit.id) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}