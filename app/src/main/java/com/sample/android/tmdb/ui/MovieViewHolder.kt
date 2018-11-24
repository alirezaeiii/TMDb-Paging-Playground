package com.sample.android.tmdb.ui

import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.sample.android.tmdb.GlideRequests
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.MovieItemBinding

class MovieViewHolder(internal val binding: MovieItemBinding,
                      private val glide: GlideRequests)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind() {
        with(binding) {
            glide.asBitmap()
                    .load("$BASE_BACKDROP_PATH${movie?.backdropPath}")
                    .centerCrop()
                    .into(object : BitmapImageViewTarget(moviePoster) {
                        override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
                            super.onResourceReady(bitmap, transition)
                            Palette.from(bitmap).generate { palette ->
                                val color = palette!!.getVibrantColor(
                                        ContextCompat.getColor(titleBackground.context,
                                                R.color.black_translucent_60))
                                titleBackground.setBackgroundColor(color)
                            }
                        }
                    })
        }

    }

    companion object {
        const val BASE_BACKDROP_PATH = "http://image.tmdb.org/t/p/w780"
        fun create(parent: ViewGroup, glide: GlideRequests, movieClickCallback: MovieClickCallback): MovieViewHolder {
            val binding: MovieItemBinding = DataBindingUtil
                    .inflate(LayoutInflater.from(parent.context),
                            R.layout.movie_item,
                            parent, false)
            with(binding) {
                poster = binding.moviePoster
                name = binding.movieName
                callback = movieClickCallback
            }
            return MovieViewHolder(binding, glide)
        }
    }
}