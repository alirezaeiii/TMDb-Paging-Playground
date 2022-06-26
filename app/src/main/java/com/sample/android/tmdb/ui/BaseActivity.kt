package com.sample.android.tmdb.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.sample.android.tmdb.R
import com.sample.android.tmdb.util.NetworkUtils
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity: DaggerAppCompatActivity() {

    @Inject
    lateinit var networkUtils: NetworkUtils

    protected abstract val networkStatusLayout: View?

    protected abstract val textViewNetworkStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleNetwork()
    }

    private fun handleNetwork() {
        networkUtils.getNetworkLiveData().observe(this) { isConnected: Boolean ->
            if (!isConnected) {
                    textViewNetworkStatus.text = getString(R.string.failed_network_msg)
                    networkStatusLayout?.visibility = View.VISIBLE
                    networkStatusLayout?.setBackgroundColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.colorStatusNotConnected, null
                        )
                    )
            } else {
                    textViewNetworkStatus.text = getString(R.string.text_connectivity)
                    networkStatusLayout?.setBackgroundColor(
                        ResourcesCompat.getColor(
                            resources, R.color.colorStatusConnected, null
                        )
                    )
                    networkStatusLayout?.animate()?.alpha(1f)
                        ?.setStartDelay(ANIMATION_DURATION.toLong())
                        ?.setDuration(ANIMATION_DURATION.toLong())
                        ?.setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                super.onAnimationEnd(animation)
                                networkStatusLayout?.visibility = View.GONE
                            }
                        })
            }
        }
    }
}

private const val ANIMATION_DURATION = 1000