package com.sample.android.tmdb.domain

import com.sample.android.tmdb.domain.model.Person
import io.reactivex.Single

interface PersonRepository {

    fun getPerson(personId: Any): Single<Person>
}