package com.sample.android.tmdb.ui.movie

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sample.android.tmdb.ui.ItemAdapter
import com.sample.android.tmdb.ui.ItemClickCallback
import com.sample.android.tmdb.vo.Movie

class MovieAdapter(
        private val itemClickCallback: ItemClickCallback<Movie>) : ItemAdapter<Movie>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            with((holder as MovieViewHolder).binding) {
                movie = it
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder.create(parent, itemClickCallback)
    }
}