package com.sample.android.tmdb.ui.detail

import android.os.Bundle
import android.view.MenuItem
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import com.sample.android.tmdb.R
import com.sample.android.tmdb.util.addFragmentToActivity

class DetailActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var detailFragment: DetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        if (savedInstanceState == null) {

            supportFragmentManager.findFragmentById(R.id.fragment_container)
                    as DetailFragment? ?: detailFragment.also {
                addFragmentToActivity(it, R.id.fragment_container)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        const val EXTRA_MOVIE = "movie"
    }
}