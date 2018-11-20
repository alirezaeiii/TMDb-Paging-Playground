package com.sample.android.tmdb.util

import io.reactivex.disposables.Disposable

object RxUtils {

    fun unsubscribe(subscription: Disposable?) {
        if (subscription != null && !subscription.isDisposed) {
            subscription.dispose()
        } // else subscription doesn't exist or already unsubscribed
    }
}
