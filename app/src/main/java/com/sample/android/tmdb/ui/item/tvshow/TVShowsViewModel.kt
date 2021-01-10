package com.sample.android.tmdb.ui.item.tvshow

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.paging.Listing
import com.sample.android.tmdb.paging.tvshow.TVShowsPageKeyRepository
import com.sample.android.tmdb.ui.TmdbViewModel
import com.sample.android.tmdb.ui.item.SortType

class TVShowsViewModel(
        api: TVShowApi,
        sortType: SortType,
        app: Application) : TmdbViewModel<TVShow>(app = app) {

    override val repoResult: LiveData<Listing<TVShow>> = liveData {
        emit(TVShowsPageKeyRepository(api = api,
                sortType = sortType,
                context = app.applicationContext).getItems(NETWORK_IO))
    }
}