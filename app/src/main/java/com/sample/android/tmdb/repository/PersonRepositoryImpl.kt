package com.sample.android.tmdb.repository

import com.sample.android.tmdb.network.PersonApi
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val personApi: PersonApi
) : PersonRepository {

    override fun getPerson(personId: Any) = personApi.getPerson(personId)
}