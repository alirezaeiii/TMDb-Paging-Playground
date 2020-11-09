package com.sample.android.tmdb.util

import android.content.Context
import com.sample.android.tmdb.R

class TmdbScreenItemCount private constructor(context: Context) {

    val maxItemCount: Int

    init {
        val deviceHeight = context.resources.displayMetrics.heightPixels
        val deviceWidth = context.resources.displayMetrics.widthPixels
        val itemHeight = context.resources.getDimension(R.dimen.column_height)
        val itemWidth = context.resources.getDimension(R.dimen.column_width)
        val heightCount = (deviceHeight / itemHeight).toInt()
        val widthCount = (deviceWidth / itemWidth).toInt()
        maxItemCount = heightCount * widthCount + 1
    }

    companion object {
        private var instance: TmdbScreenItemCount? = null

        fun getInstance(context: Context): TmdbScreenItemCount = instance
                ?: TmdbScreenItemCount(context.applicationContext).also { instance = it }
    }
}