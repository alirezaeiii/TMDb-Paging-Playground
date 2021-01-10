package com.sample.android.tmdb.ui.item.movie

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.paging.Listing
import com.sample.android.tmdb.paging.movie.MoviePageKeyRepository
import com.sample.android.tmdb.ui.TmdbViewModel
import com.sample.android.tmdb.ui.item.SortType

class MovieViewModel(
        api: MovieApi,
        sortType: SortType,
        app: Application) : TmdbViewModel<Movie>(app = app) {

    override val repoResult: LiveData<Listing<Movie>> = liveData {
        emit(MoviePageKeyRepository(api = api,
                sortType = sortType,
                context = app.applicationContext).getItems(NETWORK_IO))
    }
}