package com.sample.android.tmdb.ui.detail.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import android.view.View
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.FragmentDetailMovieBinding
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.ui.detail.DetailFragment
import org.jetbrains.annotations.Nullable
import javax.inject.Inject

class MovieDetailFragment @Inject
constructor() // Required empty public constructor
    : DetailFragment<Movie>() {

    @Inject
    @Nullable
    lateinit var movieItem: Movie

    override val viewModel by lazy {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MovieDetailViewModel(api, movieItem) as T
            }
        })[MovieDetailViewModel::class.java]
    }

    override val layoutId = R.layout.fragment_detail_movie

    override val tmdbItem: Movie by lazy { movieItem }

    override fun getViewBinding(root: View): FragmentDetailMovieBinding =
            FragmentDetailMovieBinding.bind(root).apply {
                movie = movieItem
            }
}