package com.sample.android.tmdb.ui.tvshow

import com.sample.android.tmdb.ui.MainActivity
import com.sample.android.tmdb.ui.TVShowFragment
import com.sample.android.tmdb.util.NavType

abstract class MainTVShowFragment : TVShowFragment() {

    override fun getNavType(): NavType? = (activity as MainActivity).getNavType()
}