package com.sample.android.tmdb.ui.movie

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.MovieItemBinding
import com.sample.android.tmdb.ui.ItemClickCallback
import com.sample.android.tmdb.util.layoutInflater
import com.sample.android.tmdb.vo.Movie

class MovieViewHolder(internal val binding: MovieItemBinding)
    : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup, movieClickCallback: ItemClickCallback<Movie>): MovieViewHolder {
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