package com.sample.android.tmdb.ui

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sample.android.tmdb.R
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

    protected abstract fun getLayoutID(): Int

    protected abstract fun onBindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int)

    protected abstract fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            getLayoutID() -> onBindItemViewHolder(holder, position)
            R.layout.network_state_item -> (holder as NetworkStateItemViewHolder).bindTo(networkState, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            getLayoutID() -> onCreateViewHolder(parent)
            R.layout.network_state_item -> NetworkStateItemViewHolder.create(parent)
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.network_state_item
        } else {
            getLayoutID()
        }
    }

    override fun getItemCount(): Int = super.getItemCount() + if (hasExtraRow()) 1 else 0

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