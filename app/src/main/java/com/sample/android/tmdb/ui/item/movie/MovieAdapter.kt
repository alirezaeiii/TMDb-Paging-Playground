package com.sample.android.tmdb.ui.item.movie

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sample.android.tmdb.R
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.ui.TmdbAdapter
import com.sample.android.tmdb.ui.TmdbClickCallback

class MovieAdapter(
        private val tmdbClickCallback: TmdbClickCallback<Movie>,
        retryCallback: () -> Unit)
    : TmdbAdapter<Movie>(retryCallback) {

    override val layoutID = R.layout.movie_item

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder, item: Movie?) {
        with((holder as MovieViewHolder).binding) {
            movie = item
            executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            MovieViewHolder.create(parent, tmdbClickCallback)
}