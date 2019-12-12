package com.sample.android.tmdb.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.util.NavType

abstract class MainFragment<T : TmdbItem> : BaseFragment<T>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = super.onCreateView(inflater, container, savedInstanceState)
        viewModel.showQuery("")
        return root
    }

    override fun getNavType(): NavType? = (activity as MainActivity).getNavType()
}