package com.sample.android.tmdb.domain

class Items<T : TmdbItem>(
    val popular: List<T>,
    val latest: List<T>,
    val topRated: List<T>
)