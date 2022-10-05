package com.sample.android.tmdb.network

import com.sample.android.tmdb.data.Person
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PersonApi {

    @GET("3/person/{personId}")
    fun getPerson(@Path("personId") personId: Any): Single<Person>
}