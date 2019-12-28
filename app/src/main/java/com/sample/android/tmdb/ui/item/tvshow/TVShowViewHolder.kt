package com.sample.android.tmdb.ui.item.tvshow

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.TvShowItemBinding
import com.sample.android.tmdb.ui.ItemClickCallback
import com.sample.android.tmdb.util.layoutInflater
import com.sample.android.tmdb.domain.TVShow

class TVShowViewHolder(internal val binding: TvShowItemBinding)
    : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup, itemClickCallback: ItemClickCallback<TVShow>): TVShowViewHolder {
            val binding: TvShowItemBinding = DataBindingUtil
                    .inflate(parent.context.layoutInflater,
                            R.layout.tv_show_item,
                            parent, false)
            with(binding) {
                poster = tvShowPoster
                name = tvShowName
                callback = itemClickCallback
            }
            return TVShowViewHolder(binding)
        }
    }
}