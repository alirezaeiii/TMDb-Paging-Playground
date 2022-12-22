package com.sample.android.tmdb.data.response

import com.google.gson.annotations.SerializedName

class Person(
    @SerializedName("birthday")
    val birthDay: String?,
    @SerializedName("deathday")
    val deathDay: String?,
    val id: Int,
    @SerializedName("also_known_as")
    val alsoKnowAs: Array<String>,
    val biography: String,
    @SerializedName("place_of_birth")
    val placeOfBirth: String?)