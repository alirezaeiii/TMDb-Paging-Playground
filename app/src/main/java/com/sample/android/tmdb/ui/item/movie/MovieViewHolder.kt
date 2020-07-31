package com.sample.android.tmdb.ui.item.movie

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.MovieItemBinding
import com.sample.android.tmdb.ui.TmdbClickCallback
import com.sample.android.tmdb.util.layoutInflater
import com.sample.android.tmdb.domain.Movie

class MovieViewHolder(internal val binding: MovieItemBinding)
    : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup, movieClickCallback: TmdbClickCallback<Movie>): MovieViewHolder {
            val binding: MovieItemBinding = DataBindingUtil
                    .inflate(parent.context.layoutInflater,
                            R.layout.movie_item,
                            parent, false)
            with(binding) {
                poster = moviePoster
                name = movieName
                callback = movieClickCallback
            }
            return MovieViewHolder(binding)
        }
    }
}