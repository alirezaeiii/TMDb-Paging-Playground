package com.sample.android.tmdb.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

interface Credit : Parcelable {
    val id: Any
    val credit: String
    val name: String
    val profilePath: String?
}

@Parcelize
class Cast(
    override val credit: String,
    override val name: String,
    override val profilePath: String?,
    override val id: Int
) : Credit

@Parcelize
class Crew(
    override val credit: String,
    override val name: String,
    override val profilePath: String?,
    override val id: String
) : Credit