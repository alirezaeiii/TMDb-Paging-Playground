package com.sample.android.tmdb.ui.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.inputmethod.EditorInfo
import android.widget.SearchView.OnQueryTextListener
import com.sample.android.tmdb.R
import com.sample.android.tmdb.util.NavType
import com.sample.android.tmdb.util.addFragmentToActivity
import com.sample.android.tmdb.util.toVisibility
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class SearchActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var searchMovieFragment: SearchMovieFragment

    @Inject
    lateinit var searchTVShowFragment: SearchTVShowFragment

    @Inject
    lateinit var navType: NavType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        search_view.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        // inputType & ime options seem to be ignored from XML! Set in code
        search_view.inputType = InputType.TYPE_TEXT_FLAG_CAP_WORDS
        search_view.imeOptions = search_view.imeOptions or EditorInfo.IME_ACTION_SEARCH or
                EditorInfo.IME_FLAG_NO_EXTRACT_UI or EditorInfo.IME_FLAG_NO_FULLSCREEN
        search_view.requestFocus()

        search_back.setOnClickListener {
            finishAfterTransition()
        }

        val pair = when (navType) {
            NavType.MOVIES -> Pair(searchMovieFragment, getString(R.string.search_hint_movies))
            NavType.TV_SERIES -> Pair(searchTVShowFragment, getString(R.string.search_hint_tv_shows))
        }
        val fragment = pair.first
        addFragmentToActivity(fragment, R.id.fragment_container)
        search_view.queryHint = pair.second

        search_view.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                fragment.search(query)
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                val hasQuery = query.isNotEmpty()
                toVisibility(fragment_container, hasQuery)
                if (hasQuery) {
                    fragment.search(query)
                }
                return true
            }
        })
    }
}

const val EXTRA_NAV_TYPE = "nav_type"