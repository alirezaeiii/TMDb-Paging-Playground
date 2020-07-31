package com.sample.android.tmdb.ui.search

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.network.TmdbApi
import com.sample.android.tmdb.repository.Listing
import com.sample.android.tmdb.repository.bypage.search.SearchMoviePageKeyRepository
import com.sample.android.tmdb.ui.TmdbViewModel

class SearchMovieViewModel(api: TmdbApi, app: Application) : TmdbViewModel<Movie>(app = app) {

    override val repoResult: LiveData<Listing<Movie>> = Transformations.map(query) {
        SearchMoviePageKeyRepository(api, app.applicationContext).getItems(it, NETWORK_IO)
    }
}