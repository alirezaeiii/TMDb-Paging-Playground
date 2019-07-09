package com.sample.android.tmdb.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.sample.android.tmdb.R
import com.sample.android.tmdb.repository.NetworkState
import com.sample.android.tmdb.repository.Status.RUNNING

class NetworkStateItemViewHolder(root: View)
    : RecyclerView.ViewHolder(root) {

    private val progressBar = root.findViewById<ProgressBar>(R.id.progress_bar)

    fun bindTo(networkState: NetworkState?) {
        progressBar.visibility = toVisbility(networkState?.status == RUNNING)
    }

    companion object {
        fun create(parent: ViewGroup): NetworkStateItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.network_state_item, parent, false)
            return NetworkStateItemViewHolder(view)
        }

        fun toVisbility(constraint: Boolean): Int {
            return if (constraint) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
}