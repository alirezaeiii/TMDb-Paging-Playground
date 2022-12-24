package com.sample.android.tmdb.data.response

import com.google.gson.annotations.SerializedName
import com.sample.android.tmdb.domain.model.Video

class VideoDto(
    val id: String,
    val name: String,
    val site: String,
    @SerializedName("key")
    val videoId: String,
    val type: String
)

fun List<VideoDto>.asDomainModel(): List<Video> =
    map { Video(it.id, it.name, it.site, it.videoId, it.type) }