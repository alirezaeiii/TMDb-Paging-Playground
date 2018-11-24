package com.sample.android.tmdb.ui.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView
import com.sample.android.tmdb.R
import com.sample.android.tmdb.addFragmentToActivity
import com.sample.android.tmdb.util.RxUtils
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SearchActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var searchFragment: SearchFragment

    private var mQuery: String? = null

    private lateinit var fragment: SearchFragment

    private lateinit var searchViewTextSubscription: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        if (savedInstanceState != null) {
            mQuery = savedInstanceState.getString(QUERY)
        }

        fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
                as SearchFragment? ?: searchFragment.also {
            addFragmentToActivity(it, R.id.fragment_container)
        }
    }

    override fun onNewIntent(intent: Intent) {
        val action = intent.action
        if (action == Intent.ACTION_SEARCH) {
            mQuery = intent.getStringExtra(SearchManager.QUERY)

            fragment.searchViewClicked(query = mQuery)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)

        // Inflate the options menu from XML
        menuInflater.inflate(R.menu.search_in_menu, menu)

        // Get the SearchView and set the searchable configuration
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.isIconified = false // Do not iconify the widget; expand it by default

        if (mQuery != null) {
            searchView.setQuery(mQuery, false)
        }

        searchViewTextSubscription = RxSearchView.queryTextChanges(searchView)
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe { charSequence ->
                    mQuery = charSequence.toString()
                    if (charSequence.isNotEmpty()) {
                        runOnUiThread { fragment.searchViewClicked(mQuery) }
                    }
                }

        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(QUERY, mQuery)
    }

    override fun onDestroy() {
        RxUtils.unsubscribe(searchViewTextSubscription)
        super.onDestroy()
    }

    companion object {
        private const val QUERY = "query"
    }
}