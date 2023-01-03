package com.sample.android.tmdb.ui.detail.credit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.sample.android.tmdb.domain.model.Credit
import com.sample.android.tmdb.domain.model.TmdbItem
import com.sample.android.tmdb.ui.person.PersonActivity
import com.sample.android.tmdb.domain.model.PersonWrapper
import com.sample.android.tmdb.util.Constants.PERSON_WRAPPER

class CreditClickListener<T : Credit>(
    private val context: Context,
    private val tmdbItem: TmdbItem
) : CreditClickCallback<T> {

    override fun onClick(credit: T) {
        val intent = Intent(context, PersonActivity::class.java).apply {
            putExtras(Bundle().apply {
                putParcelable(
                    PERSON_WRAPPER,
                    PersonWrapper(credit, tmdbItem.backdropUrl)
                )
            })
        }
        context.startActivity(intent)
    }
}