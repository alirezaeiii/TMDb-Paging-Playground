package com.sample.android.tmdb.repository

interface MovieRepository<T> {
    fun getItems(query: String, pageSize: Int): Listing<T>
}