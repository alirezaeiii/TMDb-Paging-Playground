package com.sample.android.tmdb.repository.bypage.search

import android.content.Context
import com.sample.android.tmdb.domain.ItemWrapper
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.repository.bypage.PageKeyedItemDataSource
import retrofit2.Call
import java.util.concurrent.Executor

class PageKeyedSearchMovieDataSource(
        private val api: MovieApi,
        private val query: String,
        retryExecutor: Executor,
        context: Context)
    : PageKeyedItemDataSource<Movie>(retryExecutor, context) {

    override fun fetchItems(page: Int): Call<ItemWrapper<Movie>> =
            api.searchItems(page, query)
}