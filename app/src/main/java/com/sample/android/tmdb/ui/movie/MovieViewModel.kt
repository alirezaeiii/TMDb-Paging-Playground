package com.sample.android.tmdb.ui.movie

import android.arch.lifecycle.Transformations
import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.repository.bypage.movie.MoviePageKeyRepository
import com.sample.android.tmdb.ui.ItemViewModel
import com.sample.android.tmdb.vo.Movie

class MovieViewModel @JvmOverloads constructor(
        dataSource: MoviesRemoteDataSource,
        sortType: SortType? = null) : ItemViewModel<Movie>() {

    override val repoResult = Transformations.map(query) {
        MoviePageKeyRepository(
                dataSource = dataSource,
                sortType = sortType,
                networkExecutor = NETWORK_IO).getItems(it, 20)
    }
}