package com.sample.android.tmdb.ui.item.movie

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.ui.item.BaseItemFragment
import com.sample.android.tmdb.ui.ItemAdapter
import com.sample.android.tmdb.ui.MainActivity
import com.sample.android.tmdb.ui.detail.EXTRA_MOVIE
import com.sample.android.tmdb.util.NavType

abstract class MovieFragment : BaseItemFragment<Movie>() {

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MovieViewModel(dataSource = dataSource,
                        sortType = sortType) as T
            }
        })[MovieViewModel::class.java]
    }

    override fun getAdapter(retryCallback: () -> Unit): ItemAdapter<Movie> = MovieAdapter(this, retryCallback)

    override val keyParcelable = EXTRA_MOVIE

    override fun getNavType(): NavType? = (activity as MainActivity).getNavType()
}