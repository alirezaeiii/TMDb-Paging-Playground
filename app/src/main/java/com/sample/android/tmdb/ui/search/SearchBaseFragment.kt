package com.sample.android.tmdb.ui.search

import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.ui.BaseFragment
import com.sample.android.tmdb.ui.ItemAdapter
import com.sample.android.tmdb.util.NavType
import com.sample.android.tmdb.util.SortType
import kotlinx.android.synthetic.main.fragment_main.*

abstract class SearchBaseFragment<T : TmdbItem> : BaseFragment<T>() {

    override val sortType: SortType? = null

    override fun incrementEspressoIdlingResource() {
        // Not required for the search fragment because the {@link MainFragment} handles
        // the logic of incrementing Espresso IdlingResource.
    }

    override fun getNavType(): NavType = (activity as SearchActivity).navType

    fun search(query: String?) {
        if (viewModel.showQuery(query)) {
            list.scrollToPosition(0)
            @Suppress("UNCHECKED_CAST")
            (list.adapter as ItemAdapter<T>).submitList(null)
        }
    }
}