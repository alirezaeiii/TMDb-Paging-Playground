package com.sample.android.tmdb.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sample.android.tmdb.ui.MainActivity
import com.sample.android.tmdb.ui.MovieFragment
import com.sample.android.tmdb.util.NavType

abstract class MainMovieFragment : MovieFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = super.onCreateView(inflater, container, savedInstanceState)
        viewModel.showQuery("")
        return root
    }

    override fun getNavType(): NavType? = (activity as MainActivity).getNavType()
}