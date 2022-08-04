package com.sample.android.tmdb.ui.detail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.ActivityDetailBinding
import com.sample.android.tmdb.ui.BaseActivity
import com.sample.android.tmdb.util.addFragmentToActivity

abstract class DetailActivity : BaseActivity() {

    protected abstract val fragment: DetailFragment

    private lateinit var binding: ActivityDetailBinding

    override val networkStatusLayout: View
        get() = binding.networkStatusLayout

    override val textViewNetworkStatus: TextView
        get() = binding.textViewNetworkStatus

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
}