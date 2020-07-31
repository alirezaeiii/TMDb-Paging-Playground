package com.sample.android.tmdb.ui.search

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.network.TmdbApi
import com.sample.android.tmdb.repository.Listing
import com.sample.android.tmdb.repository.bypage.search.SearchTVShowPageKeyRepository
import com.sample.android.tmdb.ui.TmdbViewModel

class SearchTVShowViewModel(api: TmdbApi, app: Application) : TmdbViewModel<TVShow>(app = app) {

    override val repoResult: LiveData<Listing<TVShow>> = Transformations.map(query) {
        SearchTVShowPageKeyRepository(api, app.applicationContext).getItems(it, NETWORK_IO)
    }
}