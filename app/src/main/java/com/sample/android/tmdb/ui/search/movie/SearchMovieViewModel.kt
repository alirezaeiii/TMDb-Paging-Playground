package com.sample.android.tmdb.ui.search.movie

import android.app.Application
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.paging.TmdbPageKeyRepository
import com.sample.android.tmdb.paging.search.movie.SearchMoviePageKeyRepository
import com.sample.android.tmdb.ui.search.BaseSearchViewModel

class SearchMovieViewModel(
        private val api: MovieApi,
        private val app: Application
) : BaseSearchViewModel<Movie>(app = app) {

    override fun getRepoResult(query: String): TmdbPageKeyRepository<Movie> =
            SearchMoviePageKeyRepository(api, query, app.applicationContext)
}