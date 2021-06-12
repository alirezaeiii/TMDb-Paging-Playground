package com.sample.android.tmdb.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.LinearLayout.LayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.sample.android.tmdb.R
import com.sample.android.tmdb.paging.NetworkState
import com.sample.android.tmdb.paging.Status.FAILED
import com.sample.android.tmdb.paging.Status.RUNNING
import com.sample.android.tmdb.util.TmdbScreenItemCount
import com.sample.android.tmdb.util.toVisibility

/**
 * A View Holder that can display a loading or have click action.
 * It is used to show the network state of paging.
 */
class NetworkStateViewHolder(
    private val root: View,
    retryCallback: () -> Unit
) : RecyclerView.ViewHolder(root) {

    private val networkStateLayout = root.findViewById<LinearLayout>(R.id.network_state_layout)
    private val progressBar = root.findViewById<ProgressBar>(R.id.progress_bar)
    private val retry = root.findViewById<Button>(R.id.retry_button)
    private val errorMsg = root.findViewById<TextView>(R.id.error_msg)
    private val errorIcon = root.findViewById<ImageView>(R.id.error_icon)

    init {
        retry.setOnClickListener {
            retryCallback()
        }
    }

    fun bindTo(
        position: Int,
        networkState: NetworkState?,
        refreshState: NetworkState?,
        itemCount: Int
    ) {
        when (refreshState?.status) {
            RUNNING -> {
                progressBar.visibility = View.GONE
            }
            FAILED -> {
                networkStateLayout.layoutParams = ViewGroup.LayoutParams(
                    LayoutParams(
                        LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT
                    )
                )
                networkStateLayout.setPadding(
                    0, 0, 0,
                    root.context.resources.getDimension(R.dimen.network_state_bottom_padding)
                        .toInt()
                )
                progressBar.visibility = View.GONE
            }
            else -> {
                if (itemCount <= TmdbScreenItemCount.getInstance(root.context).maxItemCount) {
                    progressBar.visibility = View.GONE
                } else {
                    networkStateLayout.layoutParams = ViewGroup.LayoutParams(
                        LayoutParams(
                            LayoutParams.MATCH_PARENT,
                            LayoutParams.WRAP_CONTENT
                        )
                    )
                    val padding =
                        root.context.resources.getDimension(R.dimen.network_state_padding).toInt()
                    networkStateLayout.setPadding(padding, padding, padding, padding)
                    progressBar.toVisibility(networkState?.status == RUNNING)
                }
            }
        }
        retry.toVisibility(networkState?.status == FAILED)
        errorMsg.toVisibility(networkState?.msg != null)
        errorMsg.text = networkState?.msg
        errorIcon.toVisibility(networkState?.status == FAILED && position == 0)
    }

    companion object {
        fun create(parent: ViewGroup, retryCallback: () -> Unit): NetworkStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.network_state_item, parent, false)
            return NetworkStateViewHolder(view, retryCallback)
        }
    }
}