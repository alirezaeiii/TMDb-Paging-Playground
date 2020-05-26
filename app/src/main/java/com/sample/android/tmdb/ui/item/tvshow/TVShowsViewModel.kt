package com.sample.android.tmdb.ui.item.tvshow

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.network.TmdbApi
import com.sample.android.tmdb.repository.Listing
import com.sample.android.tmdb.repository.bypage.tvshow.TVShowsPageKeyRepository
import com.sample.android.tmdb.ui.item.BaseItemViewModel
import com.sample.android.tmdb.util.SortType

class TVShowsViewModel(
        api: TmdbApi,
        sortType: SortType,
        app: Application) : BaseItemViewModel<TVShow>(app = app) {

    override val repoResult: LiveData<Listing<TVShow>> = Transformations.map(query) {
        TVShowsPageKeyRepository(
                api = api,
                sortType = sortType,
                context = app.applicationContext).getItems(it, NETWORK_IO)
    }
}