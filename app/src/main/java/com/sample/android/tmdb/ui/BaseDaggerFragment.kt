package com.sample.android.tmdb.ui

import com.sample.android.tmdb.network.TmdbApi
import dagger.android.support.DaggerFragment
import javax.inject.Inject

open class BaseDaggerFragment : DaggerFragment() {

    @Inject
    lateinit var api: TmdbApi
}