package com.sample.android.tmdb

import androidx.lifecycle.Observer

/**
 * simple observer that logs the latest value it receives
 */
class LoggingObserver<T> : Observer<T> {
    var value: T? = null
    override fun onChanged(t: T?) {
        this.value = t
    }
}