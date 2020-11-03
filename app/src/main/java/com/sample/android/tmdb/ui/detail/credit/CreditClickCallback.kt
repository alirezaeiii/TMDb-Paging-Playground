package com.sample.android.tmdb.ui.detail.credit

import android.os.Parcelable

interface CreditClickCallback<T : Parcelable> {
    fun onClick(credit : T)
}