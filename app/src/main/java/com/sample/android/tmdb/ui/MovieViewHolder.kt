package com.sample.android.tmdb.ui

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.MovieItemBinding

class MovieViewHolder(internal val binding: MovieItemBinding)
    : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup, movieClickCallback: MovieClickCallback): MovieViewHolder {
            val binding: MovieItemBinding = DataBindingUtil
                    .inflate(LayoutInflater.from(parent.context),
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