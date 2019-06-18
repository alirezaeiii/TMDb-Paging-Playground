package com.sample.android.tmdb.ui

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import com.sample.android.tmdb.repository.NetworkState
import com.sample.android.tmdb.vo.TmdbItem
import java.util.*

abstract class ItemAdapter<T : TmdbItem> : PagedListAdapter<T, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<T>() {
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
            Objects.equals(oldItem, newItem)

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
            oldItem.id == newItem.id
}) {

    private var networkState: NetworkState? = null

    private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED

    fun setNetworkState(newNetworkState: NetworkState?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }
}