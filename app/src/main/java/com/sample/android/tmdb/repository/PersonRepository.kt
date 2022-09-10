package com.sample.android.tmdb.repository

import com.sample.android.tmdb.domain.Person
import io.reactivex.Single

interface PersonRepository {

    fun getPerson(personId: Any): Single<Person>
}