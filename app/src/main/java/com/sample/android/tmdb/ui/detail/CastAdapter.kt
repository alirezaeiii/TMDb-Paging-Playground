package com.sample.android.tmdb.ui.detail

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.CastItemBinding
import com.sample.android.tmdb.util.layoutInflater
import com.sample.android.tmdb.domain.Cast

class CastAdapter(
        cast: List<Cast>,
        private val castClickCallback: CastClickCallback)
    : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    private var cast: List<Cast> = cast
        set(cast) {
            field = cast
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val binding: CastItemBinding = DataBindingUtil
                .inflate(parent.context.layoutInflater,
                        R.layout.cast_item,
                        parent, false)
        binding.callback = castClickCallback
        return CastViewHolder(binding)
    }

    override fun getItemCount() = cast.size

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        with(holder.binding) {
            castItem = cast[position]
            executePendingBindings()
        }
    }

    class CastViewHolder(internal val binding: CastItemBinding) :
            RecyclerView.ViewHolder(binding.root)
}