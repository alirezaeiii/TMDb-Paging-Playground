package com.sample.android.tmdb.ui.search.tvshow

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.domain.TVShow
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.paging.BasePageKeyRepository
import com.sample.android.tmdb.paging.search.tvshow.SearchTVShowPageKeyRepository
import com.sample.android.tmdb.ui.search.BaseSearchViewModel
import javax.inject.Inject

class SearchTVShowViewModel(
        private val api: TVShowApi,
        private val app: Application
) : BaseSearchViewModel<TVShow>(app = app) {

    override fun getRepoResult(query: String): BasePageKeyRepository<TVShow> =
            SearchTVShowPageKeyRepository(api, query, networkIO, app.applicationContext)

    class Factory @Inject constructor(
            private val api: TVShowApi,
            private val app: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchTVShowViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SearchTVShowViewModel(api, app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}