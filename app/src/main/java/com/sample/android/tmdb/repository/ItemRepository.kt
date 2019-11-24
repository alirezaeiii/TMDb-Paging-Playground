package com.sample.android.tmdb.repository

interface ItemRepository<T> {
    fun getItems(query: String, pageSize: Int): Listing<T>
}