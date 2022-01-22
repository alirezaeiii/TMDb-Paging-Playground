package com.sample.android.tmdb.ui

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.sample.android.tmdb.R
import com.sample.android.tmdb.ui.feed.NavType
import com.sample.android.tmdb.ui.paging.search.SearchActivity
import com.sample.android.tmdb.util.Constants
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    abstract val navType: NavType?

    protected abstract val toolbar: Toolbar

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                val searchMenuView: View = toolbar.findViewById(R.id.action_search)
                val options = ActivityOptions.makeSceneTransitionAnimation(
                    this,
                    searchMenuView, getString(R.string.transition_search_back)
                ).toBundle()

                val intent = Intent(this, SearchActivity::class.java).apply {
                    action = Intent.ACTION_SEARCH
                    putExtras(Bundle().apply {
                        putParcelable(Constants.EXTRA_NAV_TYPE, navType)
                    })
                }
                startActivity(intent, options)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}