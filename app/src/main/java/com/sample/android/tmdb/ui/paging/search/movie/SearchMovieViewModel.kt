package com.sample.android.tmdb.ui.paging.search.movie

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.data.Movie
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.paging.BasePageKeyRepository
import com.sample.android.tmdb.paging.search.movie.SearchMoviePageKeyRepository
import com.sample.android.tmdb.ui.paging.search.BaseSearchViewModel
import javax.inject.Inject

class SearchMovieViewModel(
        private val api: MovieApi,
        private val app: Application
) : BaseSearchViewModel<Movie>(app = app) {

    override fun searchRepoResult(query: String): BasePageKeyRepository<Movie> =
            SearchMoviePageKeyRepository(api, query, networkIO, app.applicationContext)

    class Factory @Inject constructor(
            private val api: MovieApi,
            private val app: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchMovieViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SearchMovieViewModel(api, app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}