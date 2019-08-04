package com.sample.android.tmdb.ui.detail.movie

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.view.View
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.FragmentDetailMovieBinding
import com.sample.android.tmdb.di.ActivityScoped
import com.sample.android.tmdb.ui.detail.DetailFragment
import com.sample.android.tmdb.vo.Movie
import org.jetbrains.annotations.Nullable
import javax.inject.Inject

@ActivityScoped
class MovieDetailFragment @Inject
constructor() // Required empty public constructor
    : DetailFragment<Movie>() {

    @Inject
    @Nullable
    lateinit var movie: Movie

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(requireActivity(), object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MovieDetailViewModel(dataSource) as T
            }
        })[MovieDetailViewModel::class.java]
    }

    override fun getLayoutId(): Int = R.layout.fragment_detail_movie

    override fun initViewBinding(root: View): FragmentDetailMovieBinding =
            FragmentDetailMovieBinding.bind(root).apply {
                movie = this@MovieDetailFragment.movie
            }

    override fun getTmdbItem(): Movie = movie
}