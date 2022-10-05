package com.sample.android.tmdb.ui.detail

import com.sample.android.tmdb.data.CreditWrapper
import com.sample.android.tmdb.data.Video
import com.sample.android.tmdb.data.VideoWrapper
import com.sample.android.tmdb.ui.BaseDetailViewModel
import com.sample.android.tmdb.ui.detail.DetailViewModel.DetailWrapper
import io.reactivex.Single

open class DetailViewModel(
    trailers: Single<VideoWrapper>,
    credits: Single<CreditWrapper>
) : BaseDetailViewModel<DetailWrapper>(
    Single.zip(trailers.map { it.videos }, credits,
        { videos, creditWrapper ->
            DetailWrapper(videos, creditWrapper)
        })
) {

    class DetailWrapper(
        val videos: List<Video>,
        val creditWrapper: CreditWrapper
    )
}