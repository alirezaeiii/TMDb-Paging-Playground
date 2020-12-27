package com.sample.android.tmdb.ui.search.tvshow

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.paging.Listing
import com.sample.android.tmdb.paging.search.tvshow.SearchTVShowPageKeyRepository
import com.sample.android.tmdb.ui.TmdbViewModel

class SearchTVShowViewModel(api: TVShowApi, app: Application) : TmdbViewModel<TVShow>(app = app) {

    override val repoResult: LiveData<Listing<TVShow>> = Transformations.map(query) {
        SearchTVShowPageKeyRepository(api, it, app.applicationContext).getItems(NETWORK_IO)
    }
}