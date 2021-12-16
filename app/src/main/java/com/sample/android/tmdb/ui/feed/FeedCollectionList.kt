package com.sample.android.tmdb.ui.feed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.sample.android.tmdb.R
import com.sample.android.tmdb.domain.Movie
import com.sample.android.tmdb.domain.TmdbItem
import com.sample.android.tmdb.ui.TmdbTheme
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

@Composable
private fun <T : TmdbItem> FeedCollection(
    feedCollection: List<T>,
    sortType: Int,
    onMoreClick: () -> Unit,
    onFeedClick: OnFeedClickListener,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .heightIn(min = 36.dp)
                .padding(start = 12.dp)
        ) {
            Text(
                text = stringResource(id = sortType),
                maxLines = 1,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
            )
            Text(
                text = "More",
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable { onMoreClick.invoke() }
                    .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 10.dp)
            )
        }
        Feeds(feedCollection, onFeedClick)
    }
}

@Composable
private fun <T : TmdbItem> Feeds(
    feeds: List<T>,
    onFeedClick: OnFeedClickListener,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(start = 2.dp, end = 2.dp)
    ) {
        items(feeds) { feed ->
            TmdbItem(feed, onFeedClick)
        }
    }
}

@Composable
private fun TmdbItem(
    tmdbItem: TmdbItem,
    onFeedClick: OnFeedClickListener
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = { onFeedClick.onClick(tmdbItem) })
            .padding(6.dp)
    ) {
        Image(
            painter = rememberImagePainter(tmdbItem.posterPath?.let { url ->
                stringResource(R.string.base_poster_path, url)
            }),
            contentDescription = null,
            modifier = Modifier
                .size(width = 120.dp, height = 180.dp)
                .border(.3.dp, Color(0xFFFFFFFF), RectangleShape),
            contentScale = ContentScale.Crop,
        )
        Text(
            text = tmdbItem.name,
            fontSize = 11.sp,
            color = MaterialTheme.colors.onSurface,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .size(width = 120.dp, height = 56.dp)
                .padding(top = 6.dp)
        )
    }
}

@Preview("default")
@Composable
fun FeedCardPreview() {
    TmdbTheme {
        val movie = Movie(1, "", null, null, null, "Movie", 1.0)
        TmdbItem(
            tmdbItem = movie,
            onFeedClick = OnFeedClickListener {},
        )
    }
}