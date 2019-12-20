package com.sample.android.tmdb.ui.movie

import com.sample.android.tmdb.ui.MainActivity
import com.sample.android.tmdb.ui.MovieFragment
import com.sample.android.tmdb.util.NavType

abstract class MainMovieFragment : MovieFragment() {

    override fun getNavType(): NavType? = (activity as MainActivity).getNavType()
}