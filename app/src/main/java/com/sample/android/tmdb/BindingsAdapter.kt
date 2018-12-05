package com.sample.android.tmdb

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.support.v7.widget.CardView
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.sample.android.tmdb.ui.MovieViewHolder

object BindingsAdapter {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindImage(cardView: CardView, url: String?) {

        Glide.with(cardView.context)
                .asBitmap()
                .load("${MovieViewHolder.BASE_BACKDROP_PATH}$url")
                .apply(RequestOptions().centerCrop())
                .into(object : BitmapImageViewTarget(cardView.findViewById(R.id.movie_poster)) {
                    override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
                        super.onResourceReady(bitmap, transition)
                        Palette.from(bitmap).generate { palette ->
                            val color = palette!!.getVibrantColor(
                                    ContextCompat.getColor(cardView.context,
                                            R.color.black_translucent_60))

                            cardView.findViewById<View>(R.id.title_background).setBackgroundColor(color)
                        }
                    }
                })
    }
}