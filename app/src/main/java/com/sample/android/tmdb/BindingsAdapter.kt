package com.sample.android.tmdb

import android.content.Intent
import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.net.Uri
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.CardView
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.sample.android.tmdb.ui.detail.CastAdapter
import com.sample.android.tmdb.vo.Cast
import com.sample.android.tmdb.vo.Video

object BindingsAdapter {

    private const val BASE_POSTER_PATH = "http://image.tmdb.org/t/p/w342"
    private const val BASE_BACKDROP_PATH = "http://image.tmdb.org/t/p/w780"
    private const val IMAGE_LOW_RES_BASE_URL = "https://image.tmdb.org/t/p/w500"

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindImage(cardView: CardView, url: String?) {

        Glide.with(cardView.context)
                .asBitmap()
                .load("$BASE_POSTER_PATH$url")
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

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindImage(imageView: AppCompatImageView, url: String?) {

        Glide.with(imageView.context)
                .load("$BASE_POSTER_PATH$url")
                .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindImage(imageView: ImageView, url: String?) {

        Glide.with(imageView.context)
                .load("$BASE_BACKDROP_PATH$url")
                .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("items")
    fun addItems(linearLayout: LinearLayout, trailers: List<Video>) {

        linearLayout.removeAllViews()
        val context = linearLayout.context

        val options = RequestOptions()
                .placeholder(R.color.colorPrimary)
                .centerCrop()
                .override(150, 150)

        for (trailer in trailers) {
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

    @JvmStatic
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

    @JvmStatic
    @BindingAdapter("items")
    fun addItems(recyclerView: RecyclerView, cast: List<Cast>) {

        val adapter = CastAdapter(cast)

        recyclerView.apply {

            layoutManager = GridLayoutManager(recyclerView.context,
                    resources.getInteger(R.integer.no_of_columns),
                    GridLayoutManager.VERTICAL,
                    false)

            setHasFixedSize(true)

            recyclerView.adapter = adapter
        }
    }

}