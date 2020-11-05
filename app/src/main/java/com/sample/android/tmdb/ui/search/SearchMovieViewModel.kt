package com.sample.android.tmdb.ui.search

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.paging.Listing
import com.sample.android.tmdb.paging.search.SearchMoviePageKeyRepository
import com.sample.android.tmdb.ui.TmdbViewModel

class SearchMovieViewModel(api: MovieApi, app: Application) : TmdbViewModel<Movie>(app = app) {

    override val repoResult: LiveData<Listing<Movie>> = Transformations.map(query) {
        SearchMoviePageKeyRepository(api, it, app.applicationContext).getItems(NETWORK_IO)
    }
}