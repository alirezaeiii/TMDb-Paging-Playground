package com.sample.android.tmdb.ui.detail

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.ActorItemBinding
import com.sample.android.tmdb.layoutInflater
import com.sample.android.tmdb.vo.Cast

class ActorAdapter(
        private val actors: List<Cast>)
    : RecyclerView.Adapter<ActorAdapter.ActorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val binding: ActorItemBinding = DataBindingUtil
                .inflate(parent.context.layoutInflater,
                        R.layout.actor_item,
                        parent, false)
        return ActorViewHolder(binding)
    }

    override fun getItemCount() = actors.size

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        with(holder.binding) {
            actor = actors[position]
            executePendingBindings()
        }
    }

    class ActorViewHolder(internal val binding: ActorItemBinding) :
            RecyclerView.ViewHolder(binding.root)
}