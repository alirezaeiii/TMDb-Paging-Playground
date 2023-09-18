package com.sample.android.tmdb.ui.person

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.domain.repository.PersonRepository
import com.sample.android.tmdb.domain.model.Credit
import com.sample.android.tmdb.domain.model.Person
import com.sample.android.tmdb.ui.BaseDetailViewModel
import javax.inject.Inject

class PersonViewModel(
    repository: PersonRepository,
    personId: Any
) : BaseDetailViewModel<Person>(repository.getPerson(personId)) {

    /**
     * Factory for constructing PersonViewModel with parameter
     */
    class Factory @Inject constructor(
        private val repository: PersonRepository,
        private val credit: Credit
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PersonViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PersonViewModel(repository, credit.id) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}