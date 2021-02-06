package com.sample.android.tmdb.ui.detail

import com.sample.android.tmdb.domain.CreditWrapper
import com.sample.android.tmdb.domain.Video
import com.sample.android.tmdb.domain.VideoWrapper
import com.sample.android.tmdb.ui.BaseViewModel
import com.sample.android.tmdb.ui.detail.DetailViewModel.DetailWrapper
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

open class DetailViewModel(
        trailers: Observable<VideoWrapper>,
        credits: Observable<CreditWrapper>
) : BaseViewModel<DetailWrapper>(Observable.zip(trailers.map { it.videos }, credits,
        BiFunction<List<Video>, CreditWrapper, DetailWrapper> { videos, creditWrapper ->
            DetailWrapper(videos, creditWrapper)
        })) {

    class DetailWrapper(
            val videos: List<Video>,
            val creditWrapper: CreditWrapper
    )
}