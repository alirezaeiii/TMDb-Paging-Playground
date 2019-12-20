package com.sample.android.tmdb.ui.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.repository.Listing
import com.sample.android.tmdb.repository.RemoteDataSource
import com.sample.android.tmdb.repository.bypage.search.SearchMoviePageKeyRepository
import com.sample.android.tmdb.ui.ItemViewModel

class SearchMovieViewModel(dataSource: RemoteDataSource) : ItemViewModel<Movie>() {

    override val repoResult: LiveData<Listing<Movie>> = Transformations.map(query) {
        SearchMoviePageKeyRepository(
                dataSource = dataSource,
                networkExecutor = NETWORK_IO).getItems(it, 20)
    }
}