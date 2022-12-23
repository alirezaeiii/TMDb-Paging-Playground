package com.sample.android.tmdb.data.response

import com.google.gson.annotations.SerializedName
import com.sample.android.tmdb.domain.model.Cast
import com.sample.android.tmdb.domain.model.Crew

class NetworkCast(
    @SerializedName("character")
    val credit: String,
    val name: String,
    @SerializedName(PROFILE_PATH)
    val profilePath: String?,
    val id: Int
)

class NetworkCrew(
    @SerializedName("job")
    val credit: String,
    val name: String,
    @SerializedName(PROFILE_PATH)
    val profilePath: String?,
    val id: String
)

fun List<NetworkCast>.asCastDomainModel(): List<Cast> = map {
    Cast(it.credit, it.name, it.profilePath, it.id)
}

fun List<NetworkCrew>.asCrewDomainModel(): List<Crew> = map {
    Crew(it.credit, it.name, it.profilePath, it.id)
}

private const val PROFILE_PATH = "profile_path"