package com.sample.android.tmdb.ui.item.tvshow

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.sample.android.tmdb.R
import com.sample.android.tmdb.ui.TmdbAdapter
import com.sample.android.tmdb.ui.TmdbClickCallback
import com.sample.android.tmdb.domain.TVShow

class TVShowAdapter(
        private val tmdbClickCallback: TmdbClickCallback<TVShow>,
        retryCallback: () -> Unit)
    : TmdbAdapter<TVShow>(retryCallback) {

    override val layoutID = R.layout.tv_show_item

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder, item: TVShow?) {
        with((holder as TVShowViewHolder).binding) {
            tvShow = item
            executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            TVShowViewHolder.create(parent, tmdbClickCallback)
}