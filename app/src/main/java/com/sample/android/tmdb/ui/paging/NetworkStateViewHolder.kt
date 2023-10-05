package com.sample.android.tmdb.ui.paging

import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.LinearLayout.LayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.sample.android.tmdb.R
import com.sample.android.tmdb.data.paging.NetworkState
import com.sample.android.tmdb.data.paging.Status.FAILED
import com.sample.android.tmdb.data.paging.Status.RUNNING
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
        networkState: NetworkState?
    ) {
        val padding = root.context.resources.getDimension(R.dimen.network_state_padding).toInt()
        if (position == 0) {
            networkStateLayout.layoutParams = ViewGroup.LayoutParams(
                LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT
                )
            )
            networkStateLayout.setPadding(0, 0, 0, padding)
            progressBar.visibility = View.GONE
        } else {
            networkStateLayout.layoutParams = ViewGroup.LayoutParams(
                LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT
                )
            )
            networkStateLayout.setPadding(padding, padding, padding, padding)
            progressBar.toVisibility(networkState?.status == RUNNING)
        }
        retry.toVisibility(networkState?.status == FAILED)
        errorMsg.toVisibility(networkState?.msg != null)
        errorMsg.text = networkState?.msg
        val orientation = root.context.resources.configuration.orientation
        errorIcon.toVisibility(
            networkState?.status == FAILED && position == 0 &&
                    orientation == Configuration.ORIENTATION_PORTRAIT
        )
    }

    companion object {
        fun create(parent: ViewGroup, retryCallback: () -> Unit): NetworkStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.network_state_item, parent, false)
            return NetworkStateViewHolder(view, retryCallback)
        }
    }
}