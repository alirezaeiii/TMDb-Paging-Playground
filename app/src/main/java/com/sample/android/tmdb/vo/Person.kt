package com.sample.android.tmdb.vo

import com.google.gson.annotations.SerializedName

class Person(
        @SerializedName("birthday")
        var birthDay: String?,
        @SerializedName("deathday")
        var deathDay: String?,
        var id: Int,
        @SerializedName("also_known_as")
        var alsoKnowAs: List<String>,
        var biography: String,
        @SerializedName("place_of_birth")
        var placeOfBirth: String?)