package com.sample.android.tmdb.ui.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.repository.Listing
import com.sample.android.tmdb.repository.bypage.search.SearchMoviePageKeyRepository
import com.sample.android.tmdb.ui.TmdbViewModel
import com.sample.android.tmdb.usecase.SearchUseCase

class SearchMovieViewModel(useCase: SearchUseCase) : TmdbViewModel<Movie>() {

    override val repoResult: LiveData<Listing<Movie>> = Transformations.map(query) {
        SearchMoviePageKeyRepository(
                useCase = useCase).getItems(it, NETWORK_IO)
    }
}