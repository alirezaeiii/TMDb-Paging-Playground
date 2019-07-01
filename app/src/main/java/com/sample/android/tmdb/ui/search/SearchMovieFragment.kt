package com.sample.android.tmdb.ui.search

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.sample.android.tmdb.di.ActivityScoped
import com.sample.android.tmdb.ui.ItemAdapter
import com.sample.android.tmdb.ui.detail.DetailActivity
import com.sample.android.tmdb.ui.movie.MovieAdapter
import com.sample.android.tmdb.ui.movie.MovieViewModel
import com.sample.android.tmdb.vo.Movie
import javax.inject.Inject

@ActivityScoped
class SearchMovieFragment @Inject
constructor() // Required empty public constructor
    : SearchBaseFragment<Movie, Movie>() {

    override fun initViewModel() {
        model = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MovieViewModel(dataSource = dataSource,
                        sortType = getSortType()) as T
            }
        })[MovieViewModel::class.java]
    }

    override fun getAdapter(): ItemAdapter<Movie> = MovieAdapter(this)

    override fun PutItemParcelable(bundle: Bundle, e: Movie) {
        bundle.putParcelable(DetailActivity.EXTRA_MOVIE, e)
    }
}