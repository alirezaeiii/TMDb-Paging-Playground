package com.sample.android.tmdb.data

import com.google.gson.annotations.SerializedName
import com.sample.android.tmdb.domain.model.Cast
import com.sample.android.tmdb.domain.model.Crew

interface NetworkCredit {
    val id: Any
    val credit: String
    val name: String
    val profilePath: String?
}

class NetworkCast(
    @SerializedName("character")
    override val credit: String,
    override val name: String,
    @SerializedName(PROFILE_PATH)
    override val profilePath: String?,
    override val id: Int
) : NetworkCredit

class NetworkCrew(
    @SerializedName("job")
    override val credit: String,
    override val name: String,
    @SerializedName(PROFILE_PATH)
    override val profilePath: String?,
    override val id: String
) : NetworkCredit

fun List<NetworkCast>.asCastDomainModel(): List<Cast> = map {
    Cast(it.credit, it.name, it.profilePath, it.id)
}

fun List<NetworkCrew>.asCrewDomainModel(): List<Crew> = map {
    Crew(it.credit, it.name, it.profilePath, it.id)
}

private const val PROFILE_PATH = "profile_path"