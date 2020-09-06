package com.sample.android.tmdb.network

import com.sample.android.tmdb.domain.Person
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface PersonApi {

    @GET("3/person/{personId}")
    fun person(@Path("personId") personId: Int): Observable<Person>
}