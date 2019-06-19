package com.sample.android.tmdb.util

import android.databinding.BindingAdapter
import android.view.View

@BindingAdapter("visibleGone")
fun View.visibleGone(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}