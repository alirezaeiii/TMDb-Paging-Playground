package com.sample.android.tmdb.ui.person

import android.os.Bundle
import com.sample.android.tmdb.R
import com.sample.android.tmdb.util.addFragmentToActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class PersonActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var personFragment: PersonFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        if (savedInstanceState == null) {

            supportFragmentManager.findFragmentById(R.id.fragment_container)
                    as PersonFragment? ?: personFragment.also {
                addFragmentToActivity(it, R.id.fragment_container)
            }
        }
    }
}
