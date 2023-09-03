package com.sample.android.tmdb.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sample.android.tmdb.ui.common.TmdbTheme
import com.sample.android.tmdb.ui.common.composeView
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SettingFragment @Inject
constructor() // Required empty public constructor
    : DaggerFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = composeView {
        TmdbTheme {
            SettingsScreen()
        }
    }
}