package com.sample.android.tmdb.ui.item

import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.ui.BaseFragment
import com.sample.android.tmdb.ui.MainActivity
import com.sample.android.tmdb.usecase.ItemUseCase
import com.sample.android.tmdb.util.NavType
import com.sample.android.tmdb.util.SortType
import javax.inject.Inject

abstract class BaseItemFragment<T : TmdbItem> : BaseFragment<T>() {

    @Inject
    lateinit var useCase: ItemUseCase

    protected abstract val sortType: SortType

    override val navType: NavType? by lazy { (activity as MainActivity).getNavType() }
}