package com.sample.android.tmdb.ui

import com.sample.android.tmdb.ui.feed.NavType
import dagger.android.support.DaggerFragment

abstract class BaseNavTypeFragment : DaggerFragment()  {

    protected abstract val navType: NavType
}