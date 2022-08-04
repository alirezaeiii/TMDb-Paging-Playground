package com.sample.android.tmdb.ui.person

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.sample.android.tmdb.R
import com.sample.android.tmdb.databinding.ActivityDetailBinding
import com.sample.android.tmdb.ui.BaseActivity
import com.sample.android.tmdb.util.addFragmentToActivity
import javax.inject.Inject

class PersonActivity : BaseActivity() {

    @Inject
    lateinit var fragment: PersonFragment

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
}
