package com.sample.android.tmdb.domain

import com.sample.android.tmdb.data.Person
import io.reactivex.Single

interface PersonRepository {

    fun getPerson(personId: Any): Single<Person>
}