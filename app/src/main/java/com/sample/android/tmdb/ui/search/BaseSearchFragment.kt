package com.sample.android.tmdb.ui.search

import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.ui.BaseFragment
import com.sample.android.tmdb.ui.TmdbAdapter
import com.sample.android.tmdb.usecase.SearchUseCase
import com.sample.android.tmdb.util.NavType
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

abstract class BaseSearchFragment<T : TmdbItem> : BaseFragment<T>() {

    @Inject
    lateinit var useCase: SearchUseCase

    override val navType: NavType by lazy { (activity as SearchActivity).navType }

    internal fun search(query: String) {
        if (viewModel.showQuery(query)) {
            list.scrollToPosition(0)
            @Suppress("UNCHECKED_CAST")
            (list.adapter as TmdbAdapter<T>).submitList(null)
        }
    }
}