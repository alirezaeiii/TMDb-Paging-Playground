package com.sample.android.tmdb.data.response

import com.google.gson.annotations.SerializedName
import com.sample.android.tmdb.domain.model.Person

class PersonResponse(
    @SerializedName("birthday")
    val birthDay: String?,
    @SerializedName("deathday")
    val deathDay: String?,
    val id: Int,
    @SerializedName("also_known_as")
    val alsoKnowAs: Array<String>,
    val biography: String,
    @SerializedName("place_of_birth")
    val placeOfBirth: String?
)

fun PersonResponse.asDomainModel(): Person =
    Person(birthDay, deathDay, id, alsoKnowAs, biography, placeOfBirth)