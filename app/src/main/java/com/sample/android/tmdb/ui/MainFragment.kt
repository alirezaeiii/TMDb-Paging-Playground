package com.sample.android.tmdb.ui

import android.os.Parcelable
import com.sample.android.tmdb.NavType
import com.sample.android.tmdb.vo.TmdbItem

abstract class MainFragment<T : TmdbItem, E : Parcelable> : BaseFragment<T, E>() {

    override fun getNavType(): NavType = (activity as MainActivity).viewModel.currentType.value!!
}