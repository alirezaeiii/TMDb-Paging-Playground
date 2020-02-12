package com.sample.android.tmdb.ui.item.movie

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.repository.Listing
import com.sample.android.tmdb.repository.bypage.movie.MoviePageKeyRepository
import com.sample.android.tmdb.ui.item.BaseItemViewModel
import com.sample.android.tmdb.usecase.ItemUseCase
import com.sample.android.tmdb.util.SortType

class MovieViewModel(
        useCase: ItemUseCase,
        sortType: SortType) : BaseItemViewModel<Movie>() {

    override val repoResult: LiveData<Listing<Movie>> = Transformations.map(query) {
        MoviePageKeyRepository(
                useCase = useCase,
                sortType = sortType).getItems(it, NETWORK_IO)
    }
}