package com.sample.android.tmdb.domain.model

import io.reactivex.Single

class CreditWrapper(
    val cast: Single<List<Cast>>,
    val crew: Single<List<Crew>>
)