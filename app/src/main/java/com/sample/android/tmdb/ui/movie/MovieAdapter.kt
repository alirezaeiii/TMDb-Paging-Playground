package com.sample.android.tmdb.ui.movie

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sample.android.tmdb.R
import com.sample.android.tmdb.ui.ItemAdapter
import com.sample.android.tmdb.ui.ItemClickCallback
import com.sample.android.tmdb.domain.Movie

class MovieAdapter(
        private val itemClickCallback: ItemClickCallback<Movie>,
        retryCallback: () -> Unit)
    : ItemAdapter<Movie>(retryCallback) {

    override fun getLayoutID(): Int = R.layout.movie_item

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            with((holder as MovieViewHolder).binding) {
                movie = it
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return MovieViewHolder.create(parent, itemClickCallback)
    }
}