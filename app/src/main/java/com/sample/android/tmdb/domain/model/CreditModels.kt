package com.sample.android.tmdb.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

interface Credit : Parcelable {
    val id: Any
    val role: String
    val name: String
    val profileUrl: String?
}

@Parcelize
class Cast(
    override val role: String,
    override val name: String,
    override val profileUrl: String?,
    override val id: Int
) : Credit

@Parcelize
class Crew(
    override val role: String,
    override val name: String,
    override val profileUrl: String?,
    override val id: String
) : Credit