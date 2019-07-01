package com.sample.android.tmdb.ui.search

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v4.view.ViewCompat
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.sample.android.tmdb.SortType
import com.sample.android.tmdb.ui.BaseFragment
import com.sample.android.tmdb.ui.detail.DetailActivity
import com.sample.android.tmdb.ui.movie.MovieAdapter
import com.sample.android.tmdb.vo.TmdbItem
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_main.*

abstract class SearchBaseFragment<T : TmdbItem, E : Parcelable> : BaseFragment<T, E>() {

    override fun getSortType(): SortType? = null

    fun searchViewClicked(query: String?) {
        if (model.showQuery(query)) {
            list.scrollToPosition(0)
            (list.adapter as? MovieAdapter)?.submitList(null)
        }
    }

    override fun shouldIncrementEspressoIdlingResource() =
            (activity as SearchActivity).search_view.query.isNotEmpty()

    override fun onClick(e: E, poster: ImageView, name: TextView) {
        val intent = Intent(activity, DetailActivity::class.java).apply {
            putExtras(Bundle().apply {
                PutItemParcelable(this, e)
                putParcelable(DetailActivity.EXTRA_NAV_TYPE, (activity as SearchActivity).navType)
            })
        }
        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                requireActivity(),

                // Now we provide a list of Pair items which contain the view we can transitioning
                // from, and the name of the view it is transitioning to, in the launched activity
                Pair<View, String>(poster, ViewCompat.getTransitionName(poster)),
                Pair<View, String>(name, ViewCompat.getTransitionName(name)))

        // Now we can start the Activity, providing the activity options as a bundle
        ActivityCompat.startActivity(requireContext(), intent, activityOptions.toBundle())
    }
}