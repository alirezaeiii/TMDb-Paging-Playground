package com.sample.android.tmdb.ui.detail

import com.sample.android.tmdb.domain.CreditWrapper
import com.sample.android.tmdb.domain.Video
import com.sample.android.tmdb.domain.VideoWrapper
import com.sample.android.tmdb.ui.BaseViewModel
import com.sample.android.tmdb.ui.detail.DetailViewModel.DetailWrapper
import io.reactivex.Single

open class DetailViewModel(
    trailers: Single<VideoWrapper>,
    credits: Single<CreditWrapper>
) : BaseViewModel<DetailWrapper>(
    Single.zip(trailers.map { it.videos }, credits,
        { videos, creditWrapper ->
            DetailWrapper(videos, creditWrapper)
        })
) {

    init {
        sendRequest()
    }

    class DetailWrapper(
        val videos: List<Video>,
        val creditWrapper: CreditWrapper
    )
}