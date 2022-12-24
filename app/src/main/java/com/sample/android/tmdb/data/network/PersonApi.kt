package com.sample.android.tmdb.data.network

import com.sample.android.tmdb.data.response.PersonDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PersonApi {

    @GET("3/person/{personId}")
    fun getPerson(@Path("personId") personId: Any): Single<PersonDto>
}