package com.sample.android.tmdb.ui.movie

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.sample.android.tmdb.NavType
import com.sample.android.tmdb.ui.BaseFragment
import com.sample.android.tmdb.ui.ItemAdapter
import com.sample.android.tmdb.ui.MainActivity
import com.sample.android.tmdb.ui.detail.DetailActivity.Companion.EXTRA_MOVIE
import com.sample.android.tmdb.vo.Movie

abstract class MovieFragment : BaseFragment<Movie, Movie>() {

    override fun initViewModel() {
        model = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MovieViewModel(dataSource = dataSource,
                        sortType = getSortType()) as T
            }
        })[MovieViewModel::class.java]
        model.showQuery("")
    }

    override fun getAdapter(): ItemAdapter<Movie> = MovieAdapter(this)

    override fun putItemParcelable(bundle: Bundle, e: Movie) {
        bundle.putParcelable(EXTRA_MOVIE, e)
    }

    override fun getNavType(): NavType = (activity as MainActivity).viewModel.currentType.value!!
}