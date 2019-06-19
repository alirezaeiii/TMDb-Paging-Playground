package com.sample.android.tmdb.ui.tvshow

import android.arch.lifecycle.Transformations
import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.repository.MoviesRemoteDataSource
import com.sample.android.tmdb.repository.bypage.TVShowsPageKeyRepository
import com.sample.android.tmdb.ui.ItemViewModel
import com.sample.android.tmdb.vo.TVShow

class TVShowsViewModel @JvmOverloads constructor(
        dataSource: MoviesRemoteDataSource,
        sortType: SortType? = null) : ItemViewModel<TVShow>() {

    override val repoResult = Transformations.map(query) {
        TVShowsPageKeyRepository(
                dataSource = dataSource,
                sortType = sortType,
                networkExecutor = NETWORK_IO).getItems(it, 20)
    }
}