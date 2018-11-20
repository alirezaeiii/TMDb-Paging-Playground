package com.sample.android.tmdb

import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

fun AppCompatActivity.setupActionBar(toolbar : Toolbar, action: ActionBar.() -> Unit) {
    setSupportActionBar(toolbar)
    supportActionBar?.run {
        action()
    }
}
