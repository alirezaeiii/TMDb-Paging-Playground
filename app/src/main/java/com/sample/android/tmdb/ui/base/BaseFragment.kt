package com.sample.android.tmdb.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerFragment

open class BaseFragment<VB: ViewDataBinding>: DaggerFragment() {

    private var _binding: VB? = null

    protected val binding get() = _binding!!

    protected open fun setBinding(): VB? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = this.setBinding()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}