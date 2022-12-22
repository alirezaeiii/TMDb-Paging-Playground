package com.sample.android.tmdb.ui.detail

import com.sample.android.tmdb.data.Video
import com.sample.android.tmdb.data.VideoWrapper
import com.sample.android.tmdb.domain.model.Cast
import com.sample.android.tmdb.domain.model.CreditWrapper
import com.sample.android.tmdb.domain.model.Crew
import com.sample.android.tmdb.ui.BaseDetailViewModel
import com.sample.android.tmdb.ui.detail.DetailViewModel.DetailWrapper
import io.reactivex.Single

open class DetailViewModel(
    trailers: Single<VideoWrapper>,
    credits: CreditWrapper
) : BaseDetailViewModel<DetailWrapper>(
    Single.zip(trailers.map { it.videos }, credits.cast, credits.crew) { videos, cast, crew ->
        DetailWrapper(videos, cast, crew)
    }
) {
    class DetailWrapper(
        val videos: List<Video>,
        val cast: List<Cast>,
        val crew: List<Crew>
    )
}