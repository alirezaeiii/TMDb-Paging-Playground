package com.sample.android.tmdb.ui

import com.sample.android.tmdb.NavType
import com.sample.android.tmdb.util.EspressoIdlingResource
import com.sample.android.tmdb.vo.TmdbItem

abstract class MainFragment<T : TmdbItem> : BaseFragment<T>() {

    override fun getNavType(): NavType? = (activity as MainActivity).getNavType()

    override fun incrementEspressoIdlingResource() {
        // The network request might be handled in a different thread so make sure Espresso knows
        // that the app is busy until the response is handled.
        EspressoIdlingResource.increment() // App is busy until further notice
    }
}