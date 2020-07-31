package com.sample.android.tmdb.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.sample.android.tmdb.R
import com.sample.android.tmdb.repository.NetworkState
import com.sample.android.tmdb.repository.Status.FAILED
import com.sample.android.tmdb.repository.Status.RUNNING

/**
 * A View Holder that can display a loading or have click action.
 * It is used to show the network state of paging.
 */
class NetworkStateItemViewHolder(root: View,
                                 retryCallback: () -> Unit)
    : RecyclerView.ViewHolder(root) {

    private val progressBar = root.findViewById<ProgressBar>(R.id.progress_bar)
    private val retry = root.findViewById<Button>(R.id.retry_button)
    private val errorMsg = root.findViewById<TextView>(R.id.error_msg)

    init {
        retry.setOnClickListener {
            retryCallback()
        }
    }

    fun bindTo(networkState: NetworkState?, position: Int) {
        progressBar.visibility = toVisibility(networkState?.status == RUNNING && position != 0)
        retry.visibility = toVisibility(networkState?.status == FAILED)
        errorMsg.visibility = toVisibility(networkState?.msg != null)
        errorMsg.text = networkState?.msg
    }

    companion object {
        fun create(parent: ViewGroup, retryCallback: () -> Unit): NetworkStateItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.network_state_item, parent, false)
            return NetworkStateItemViewHolder(view, retryCallback)
        }

        fun toVisibility(constraint: Boolean): Int {
            return if (constraint) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
}