package com.sample.android.tmdb.data.response

import com.google.gson.annotations.SerializedName
import com.sample.android.tmdb.domain.model.Video
import com.sample.android.tmdb.util.Constants.ID
import com.sample.android.tmdb.util.Constants.NAME

class VideoResponse(
    @SerializedName(ID)
    val id: String,
    @SerializedName(NAME)
    val name: String,
    @SerializedName("site")
    val site: String,
    @SerializedName("key")
    val videoId: String,
    @SerializedName("type")
    val type: String
)

fun List<VideoResponse>.asDomainModel(): List<Video> =
    map { Video(it.id, it.name, it.site, it.videoId, it.type) }