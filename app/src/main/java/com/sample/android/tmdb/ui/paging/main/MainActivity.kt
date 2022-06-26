package com.sample.android.tmdb.ui.paging.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.ActivityMainBinding
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.ui.BaseNavTypeActivity
import com.sample.android.tmdb.util.addFragmentToActivity
import com.sample.android.tmdb.util.setupActionBar

abstract class MainActivity<T: TmdbItem> : BaseNavTypeActivity() {

    private var _binding: ActivityMainBinding? = null

    private val binding get() = _binding!!

    protected abstract val fragment: BaseItemFragment<T>

    protected abstract val screenTitle: String

    override val toolbar: Toolbar
        get() = binding.toolbar

    override val networkStatusLayout: View?
        get() = _binding?.networkStatusLayout

    override val textViewNetworkStatus: TextView
        get() = binding.textViewNetworkStatus

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBar(binding.toolbar) {
            setDisplayHomeAsUpEnabled(true)
        }
        title = screenTitle
        if (savedInstanceState == null) {
            addFragmentToActivity(fragment, R.id.fragment_container)
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}