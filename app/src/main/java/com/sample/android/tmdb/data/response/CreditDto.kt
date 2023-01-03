package com.sample.android.tmdb.data.response

import com.google.gson.annotations.SerializedName
import com.sample.android.tmdb.domain.model.Cast
import com.sample.android.tmdb.domain.model.Crew
import com.sample.android.tmdb.util.Constants
import com.sample.android.tmdb.util.Constants.BASE_POSTER_PATH

class NetworkCast(
    @SerializedName("character")
    val role: String,
    val name: String,
    @SerializedName(PROFILE_PATH)
    val profilePath: String?,
    val id: Int
)

class NetworkCrew(
    @SerializedName("job")
    val role: String,
    val name: String,
    @SerializedName(PROFILE_PATH)
    val profilePath: String?,
    val id: String
)

fun List<NetworkCast>.asCastDomainModel(): List<Cast> = map {
    Cast(it.role, it.name, String.format(BASE_POSTER_PATH, it.profilePath), it.id)
}

fun List<NetworkCrew>.asCrewDomainModel(): List<Crew> = map {
    Crew(it.role, it.name, String.format(BASE_POSTER_PATH, it.profilePath), it.id)
}

private const val PROFILE_PATH = "profile_path"