package com.sample.android.tmdb.ui.item.tvshow

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.repository.Listing
import com.sample.android.tmdb.repository.bypage.tvshow.TVShowsPageKeyRepository
import com.sample.android.tmdb.ui.item.BaseItemViewModel
import com.sample.android.tmdb.usecase.ItemUseCase
import com.sample.android.tmdb.util.SortType

class TVShowsViewModel(
        useCase: ItemUseCase,
        sortType: SortType) : BaseItemViewModel<TVShow>() {

    override val repoResult: LiveData<Listing<TVShow>> = Transformations.map(query) {
        TVShowsPageKeyRepository(
                useCase = useCase,
                sortType = sortType).getItems(it, NETWORK_IO)
    }
}