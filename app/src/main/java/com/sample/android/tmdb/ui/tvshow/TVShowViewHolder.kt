package com.sample.android.tmdb.ui.tvshow

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.TvShowItemBinding
import com.sample.android.tmdb.util.layoutInflater

class TVShowViewHolder(internal val binding: TvShowItemBinding)
    : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): TVShowViewHolder {
            val binding: TvShowItemBinding = DataBindingUtil
                    .inflate(parent.context.layoutInflater,
                            R.layout.tv_show_item,
                            parent, false)
            with(binding) {
                poster = tvShowPoster
                name = tvShowName
            }
            return TVShowViewHolder(binding)
        }
    }
}