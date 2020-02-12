package com.sample.android.tmdb.ui.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.repository.Listing
import com.sample.android.tmdb.repository.bypage.search.SearchTVShowPageKeyRepository
import com.sample.android.tmdb.ui.TmdbViewModel
import com.sample.android.tmdb.usecase.SearchUseCase

class SearchTVShowViewModel(useCase: SearchUseCase) : TmdbViewModel<TVShow>() {

    override val repoResult: LiveData<Listing<TVShow>> = Transformations.map(query) {
        SearchTVShowPageKeyRepository(
                useCase = useCase).getItems(it, NETWORK_IO)
    }
}