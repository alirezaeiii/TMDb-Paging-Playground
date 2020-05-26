package com.sample.android.tmdb.ui.item.movie

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.network.TmdbApi
import com.sample.android.tmdb.repository.Listing
import com.sample.android.tmdb.repository.bypage.movie.MoviePageKeyRepository
import com.sample.android.tmdb.ui.item.BaseItemViewModel
import com.sample.android.tmdb.util.SortType

class MovieViewModel(
        api: TmdbApi,
        sortType: SortType,
        app: Application) : BaseItemViewModel<Movie>(app = app) {

    override val repoResult: LiveData<Listing<Movie>> = Transformations.map(query) {
        MoviePageKeyRepository(
                api = api,
                sortType = sortType,
                context = app.applicationContext).getItems(it, NETWORK_IO)
    }
}