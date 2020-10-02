package com.sample.android.tmdb.ui

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.TmdbItemBinding
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.util.layoutInflater

class TmdbViewHolder(internal val binding: TmdbItemBinding)
    : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun <T : TmdbItem> create(parent: ViewGroup, movieClickCallback: TmdbClickCallback<T>): TmdbViewHolder {
            val binding: TmdbItemBinding = DataBindingUtil
                    .inflate(parent.context.layoutInflater,
                            R.layout.tmdb_item,
                            parent, false)
            with(binding) {
                poster = itemPoster
                name = itemName
                callback = movieClickCallback
            }
            return TmdbViewHolder(binding)
        }
    }
}