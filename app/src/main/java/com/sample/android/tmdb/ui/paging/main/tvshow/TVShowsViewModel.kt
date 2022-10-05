package com.sample.android.tmdb.ui.paging.main.tvshow

import android.app.Application
import com.sample.android.tmdb.data.TVShow
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.paging.BasePageKeyRepository
import com.sample.android.tmdb.paging.tvshow.TVShowsPageKeyRepository
import com.sample.android.tmdb.ui.paging.main.BaseItemViewModel
import com.sample.android.tmdb.ui.paging.main.SortType

class TVShowsViewModel(
        api: TVShowApi,
        sortType: SortType,
        app: Application
) : BaseItemViewModel<TVShow>(app = app) {

    override val mainRepoResult: BasePageKeyRepository<TVShow> = TVShowsPageKeyRepository(api = api,
            sortType = sortType,
            retryExecutor = networkIO,
            context = app.applicationContext)
}