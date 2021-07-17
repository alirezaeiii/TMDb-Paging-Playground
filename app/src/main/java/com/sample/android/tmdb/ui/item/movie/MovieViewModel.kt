package com.sample.android.tmdb.ui.item.movie

import android.app.Application
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.network.MovieApi
import com.sample.android.tmdb.paging.BasePageKeyRepository
import com.sample.android.tmdb.paging.movie.MoviePageKeyRepository
import com.sample.android.tmdb.ui.item.BaseItemViewModel
import com.sample.android.tmdb.ui.item.SortType

class MovieViewModel(
        api: MovieApi,
        sortType: SortType,
        app: Application
) : BaseItemViewModel<Movie>(app = app) {

    override val baseRepoResult: BasePageKeyRepository<Movie> = MoviePageKeyRepository(api = api,
            sortType = sortType,
            retryExecutor = networkIo,
            context = app.applicationContext)
}