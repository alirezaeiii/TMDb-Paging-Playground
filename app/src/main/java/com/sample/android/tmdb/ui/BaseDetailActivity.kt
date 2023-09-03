package com.sample.android.tmdb.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.ActivityDetailBinding
import com.sample.android.tmdb.util.addFragmentToActivity
import dagger.android.support.DaggerFragment

abstract class BaseDetailActivity: BaseActivity() {

    private lateinit var binding: ActivityDetailBinding

    override val networkStatusLayout: View
        get() = binding.networkStatusLayout

    override val textViewNetworkStatus: TextView
        get() = binding.textViewNetworkStatus

    protected abstract val fragment: DaggerFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            addFragmentToActivity(fragment, R.id.fragment_container)
        }
    }
}