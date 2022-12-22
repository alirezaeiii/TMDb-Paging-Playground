package com.sample.android.tmdb.ui.paging.main.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.android.tmdb.domain.model.Movie
import com.sample.android.tmdb.data.network.MovieApi
import com.sample.android.tmdb.ui.feed.NavType
import com.sample.android.tmdb.ui.paging.main.BaseItemFragment
import javax.inject.Inject

abstract class MovieFragment : BaseItemFragment<Movie>() {

    @Inject
    lateinit var api: MovieApi

    override val viewModel by lazy {
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MovieViewModel(api, sortType, requireNotNull(activity).application) as T
            }
        })[MovieViewModel::class.java]
    }

    override val navType: NavType
        get() = NavType.MOVIES
}