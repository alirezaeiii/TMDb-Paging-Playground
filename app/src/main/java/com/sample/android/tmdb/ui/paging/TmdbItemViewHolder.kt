package com.sample.android.tmdb.ui.paging

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.android.tmdb.databinding.TmdbItemBinding
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.util.layoutInflater

class TmdbItemViewHolder(val binding: TmdbItemBinding)
    : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun <T : TmdbItem> create(parent: ViewGroup, tmdbClickCallback: TmdbClickCallback<T>): TmdbItemViewHolder {
            val binding: TmdbItemBinding = TmdbItemBinding.inflate(parent.context.layoutInflater,
                    parent, false)
            with(binding) {
                poster = cardPoster
                callback = tmdbClickCallback
            }
            return TmdbItemViewHolder(binding)
        }
    }
}