package com.sample.android.tmdb.ui.search

import android.os.Parcelable
import com.sample.android.tmdb.NavType
import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.ui.BaseFragment
import com.sample.android.tmdb.ui.ItemAdapter
import com.sample.android.tmdb.vo.TmdbItem
import kotlinx.android.synthetic.main.fragment_main.*

abstract class SearchBaseFragment<T : TmdbItem, E : Parcelable> : BaseFragment<T, E>() {

    override fun getSortType(): SortType? = null

    override fun incrementEspressoIdlingResource() {
        // Not required for the search fragment because the {@link MainFragment} handles
        // the logic of incrementing Espresso IdlingResource .
    }

    override fun getNavType(): NavType = (activity as SearchActivity).navType

    fun searchViewClicked(query: String?) {
        if (model.showQuery(query)) {
            list.scrollToPosition(0)
            (list.adapter as ItemAdapter<T>).submitList(null)
        }
    }
}