package com.sample.android.tmdb.ui.tvshow

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sample.android.tmdb.ui.ItemAdapter
import com.sample.android.tmdb.vo.TVShow

class TVShowAdapter : ItemAdapter<TVShow>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            with((holder as TVShowViewHolder).binding) {
                tvShow = it
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TVShowViewHolder.create(parent)
    }
}