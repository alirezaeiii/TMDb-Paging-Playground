package com.sample.android.tmdb.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.sample.android.tmdb.R

object Dimens {

    val PaddingMicro: Dp
        @Composable get() = dimensionResource(R.dimen.padding_micro)

    val PaddingSmall: Dp
        @Composable get() = dimensionResource(R.dimen.padding_small)

    val PaddingMedium: Dp
        @Composable get() = dimensionResource(R.dimen.padding_medium)

    val PaddingNormal: Dp
        @Composable get() = dimensionResource(R.dimen.padding_normal)

    val PaddingLarge: Dp
        @Composable get() = dimensionResource(R.dimen.padding_large)
}