package com.sample.android.tmdb.repository

import com.sample.android.tmdb.vo.Movie

interface MovieRepository {
    fun getMovies(query: String, pageSize: Int): Listing<Movie>
}