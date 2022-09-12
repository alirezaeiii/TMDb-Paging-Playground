package com.sample.android.tmdb.domain

import com.google.gson.annotations.SerializedName

class Video(
        val id: String,
        val name: String,
        val site: String,
        @SerializedName("key")
        val videoId: String,
        val type: String) {

    companion object {
        private const val SITE_YOUTUBE = "YouTube"
        private const val YOUTUBE_VIDEO_URL = "http://www.youtube.com/watch?v=%1\$s"
        private const val YOUTUBE_THUMBNAIL_URL = "http://img.youtube.com/vi/%1\$s/0.jpg"

        fun getUrl(video: Video) = if (SITE_YOUTUBE.equals(video.site, ignoreCase = true))
            String.format(YOUTUBE_VIDEO_URL, video.videoId) else ""

        fun getThumbnailUrl(video: Video) = if (SITE_YOUTUBE.equals(video.site, ignoreCase = true))
            String.format(YOUTUBE_THUMBNAIL_URL, video.videoId) else ""
    }
}