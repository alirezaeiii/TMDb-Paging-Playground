package com.sample.android.tmdb.ui.detail.credit

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.CreditItemBinding
import com.sample.android.tmdb.domain.Credit
import com.sample.android.tmdb.util.layoutInflater

class CreditAdapter<T : Credit>(
        credits: ArrayList<T>,
        private val creditClickCallback: CreditClickCallback)
    : RecyclerView.Adapter<CreditAdapter.CastViewHolder>() {

    private var credits: List<T> = credits
        set(cast) {
            field = cast
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val binding: CreditItemBinding = DataBindingUtil
                .inflate(parent.context.layoutInflater,
                        R.layout.credit_item,
                        parent, false)
        binding.callback = creditClickCallback
        return CastViewHolder(binding)
    }

    override fun getItemCount() = credits.size

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        with(holder.binding) {
            credit = credits[position]
            executePendingBindings()
        }
    }

    class CastViewHolder(internal val binding: CreditItemBinding) :
            RecyclerView.ViewHolder(binding.root)
}