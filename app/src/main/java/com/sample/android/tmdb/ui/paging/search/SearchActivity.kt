package com.sample.android.tmdb.ui.paging.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.inputmethod.EditorInfo
import android.widget.SearchView.OnQueryTextListener
import com.sample.android.tmdb.R
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.util.replaceFragmentInActivity
import com.sample.android.tmdb.util.toVisibility
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*

abstract class SearchActivity<T: TmdbItem> : DaggerAppCompatActivity() {

    protected abstract val fragment: BaseSearchFragment<T>

    protected abstract val hintId: Int

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

        search_view.queryHint = getString(hintId)
        replaceFragmentInActivity(fragment, R.id.fragment_container)

        search_view.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                fragment.search(query)
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                val hasQuery = query.isNotEmpty()
                fragment_container.toVisibility(hasQuery)
                if (hasQuery) {
                    fragment.search(query)
                } else {
                    fragment.observeRefreshState()
                }
                return true
            }
        })
    }
}