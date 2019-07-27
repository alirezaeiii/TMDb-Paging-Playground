package com.sample.android.tmdb.ui.tvshow

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sample.android.tmdb.R
import com.sample.android.tmdb.ui.ItemAdapter
import com.sample.android.tmdb.ui.ItemClickCallback
import com.sample.android.tmdb.vo.TVShow

class TVShowAdapter(
        private val itemClickCallback: ItemClickCallback<TVShow>,
        retryCallback: () -> Unit)
    : ItemAdapter<TVShow>(retryCallback) {

    override fun getLayoutID(): Int = R.layout.tv_show_item

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            with((holder as TVShowViewHolder).binding) {
                tvShow = it
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return TVShowViewHolder.create(parent, itemClickCallback)
    }
}