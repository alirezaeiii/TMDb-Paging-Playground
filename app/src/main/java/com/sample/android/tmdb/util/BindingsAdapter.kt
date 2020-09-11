package com.sample.android.tmdb.util

import android.content.Intent
import androidx.databinding.BindingAdapter
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.cardview.widget.CardView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.sample.android.tmdb.R
import com.sample.android.tmdb.domain.Video
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("movieImageUrl")
fun bindImageMovie(cardView: CardView, url: String?) {
    Glide.with(cardView.context)
            .asBitmap()
            .load(url)
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

@BindingAdapter("tvShowImageUrl")
fun bindImageTvShow(cardView: CardView, url: String?) {
    Glide.with(cardView.context)
            .asBitmap()
            .load(url)
            .apply(RequestOptions().centerCrop())
            .into(object : BitmapImageViewTarget(cardView.findViewById(R.id.tv_show_poster)) {
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

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, url: String?) {
    Glide.with(imageView.context).load(url).into(imageView)
}

@BindingAdapter("items")
fun addItems(linearLayout: LinearLayout, trailers: List<Video>?) {
    linearLayout.removeAllViews()
    val context = linearLayout.context

    val options = RequestOptions()
            .placeholder(R.color.colorPrimary)
            .centerCrop()
            .override(150, 150)


    trailers?.forEach { trailer ->
        val thumbContainer = context.layoutInflater.inflate(R.layout.video, linearLayout, false)
        val thumbView = thumbContainer.findViewById<ImageView>(R.id.video_thumb)

        thumbView.apply {
            setOnClickListener {
                val playVideoIntent = Intent(Intent.ACTION_VIEW, Uri.parse(Video.getUrl(trailer)))
                context.startActivity(playVideoIntent)
            }
        }

        Glide.with(context)
                .load(Video.getThumbnailUrl(trailer))
                .apply(options)
                .into(thumbView)

        linearLayout.addView(thumbContainer)
    }
}

@BindingAdapter("profileUrl")
fun bindProfileImage(imageView: ImageView, url: String?) {
    val options = RequestOptions()
            .centerCrop()
            .error(R.drawable.ic_error_outline_black_36dp)

    Glide.with(imageView.context)
            .load("$IMAGE_LOW_RES_BASE_URL$url")
            .apply(options)
            .into(imageView)
}

@BindingAdapter("profileUrl")
fun bindProfileImage(imageView: CircleImageView, url: String?) {
    Glide.with(imageView.context)
            .load("$IMAGE_LOW_RES_BASE_URL$url")
            .apply(RequestOptions()
                    .error(R.drawable.ic_error_outline_white_96dp))
            .into(imageView)
}

@BindingAdapter("goneIfNull")
fun goneIfNull(view: View, it: Any?) {
    view.visibility = if (it == null) View.GONE else View.VISIBLE
}

@BindingAdapter("visibleGone")
fun visibleGone(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

private const val IMAGE_LOW_RES_BASE_URL = "https://image.tmdb.org/t/p/w500"