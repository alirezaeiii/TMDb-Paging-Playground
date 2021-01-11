package com.sample.android.tmdb.ui.search.tvshow

import android.app.Application
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.paging.TmdbPageKeyRepository
import com.sample.android.tmdb.paging.search.tvshow.SearchTVShowPageKeyRepository
import com.sample.android.tmdb.ui.search.BaseSearchViewModel

class SearchTVShowViewModel(
        private val api: TVShowApi,
        private val app: Application
) : BaseSearchViewModel<TVShow>(app = app) {

    override fun getRepoResult(query: String): TmdbPageKeyRepository<TVShow> =
            SearchTVShowPageKeyRepository(api, query, app.applicationContext)
}