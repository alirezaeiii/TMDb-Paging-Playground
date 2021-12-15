package com.sample.android.tmdb.ui.feed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sample.android.tmdb.R
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.ui.paging.main.MainActivity
import com.sample.android.tmdb.ui.paging.main.SortType
import com.sample.android.tmdb.util.Constants

@Composable
fun <T : TmdbItem> FeedCollectionList(
    context: Context,
    navType: NavType,
    collection: List<List<T>>,
    onFeedClick: OnFeedClickListener
) {
    LazyColumn {

        itemsIndexed(collection) { index, feedCollection ->
            val pair = when (index) {
                0 -> Pair(R.string.text_popular, SortType.MOST_POPULAR)
                1 -> Pair(R.string.text_upcoming, SortType.UPCOMING)
                2 -> Pair(R.string.text_highest_rate, SortType.HIGHEST_RATED)
                else -> throw RuntimeException("Invalid index for collection")
            }
            FeedCollection(
                feedCollection = feedCollection,
                sortType = pair.first,
                onMoreClick = {
                    val intent = Intent(context, MainActivity::class.java).apply {
                        putExtras(Bundle().apply {
                            putParcelable(Constants.EXTRA_SORT_TYPE, pair.second)
                            putParcelable(Constants.EXTRA_NAV_TYPE, navType)
                        })
                    }
                    context.startActivity(intent)
                },
                onFeedClick = onFeedClick
            )
        }
    }
}