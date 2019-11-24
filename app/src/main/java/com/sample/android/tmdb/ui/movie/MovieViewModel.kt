package com.sample.android.tmdb.ui.movie

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.sample.android.tmdb.util.SortType
import com.sample.android.tmdb.repository.Listing
import com.sample.android.tmdb.repository.RemoteDataSource
import com.sample.android.tmdb.repository.bypage.movie.MoviePageKeyRepository
import com.sample.android.tmdb.ui.ItemViewModel
import com.sample.android.tmdb.domain.Movie

class MovieViewModel @JvmOverloads constructor(
        dataSource: RemoteDataSource,
        sortType: SortType? = null) : ItemViewModel<Movie>() {

    override val repoResult: LiveData<Listing<Movie>> = Transformations.map(query) {
        MoviePageKeyRepository(
                dataSource = dataSource,
                sortType = sortType,
                networkExecutor = NETWORK_IO).getItems(it, 20)
    }
}