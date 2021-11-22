package com.sample.android.tmdb.ui.detail.credit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.sample.android.tmdb.domain.Credit
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.ui.person.PERSON_WRAPPER
import com.sample.android.tmdb.ui.person.PersonActivity
import com.sample.android.tmdb.ui.person.PersonWrapper
import com.sample.android.tmdb.util.Firebase
import com.sample.android.tmdb.util.Firebase.Companion.ANALYTICS_PERSON_SCREEN

class CreditClickListener<T : Credit>(
    private val context: Context,
    private val tmdbItem: TmdbItem,
    private val firebase: Firebase
) : CreditClickCallback<T> {

    override fun onClick(credit: T) {
        firebase.logEventScreenView("${ANALYTICS_PERSON_SCREEN}_${credit::class.java.simpleName}")
        val intent = Intent(context, PersonActivity::class.java).apply {
            putExtras(Bundle().apply {
                putParcelable(
                    PERSON_WRAPPER,
                    PersonWrapper(credit, tmdbItem.backdropPath)
                )
            })
        }
        context.startActivity(intent)
    }
}