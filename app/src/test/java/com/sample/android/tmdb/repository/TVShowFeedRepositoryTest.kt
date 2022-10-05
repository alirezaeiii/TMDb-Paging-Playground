package com.sample.android.tmdb.repository

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.android.tmdb.TestCoroutineRule
import com.sample.android.tmdb.data.ItemWrapper
import com.sample.android.tmdb.network.TVShowApi
import com.sample.android.tmdb.util.ViewState
import com.sample.android.tmdb.util.isNetworkAvailable
import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class TVShowFeedRepositoryTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var tvShowApi: TVShowApi

    @Before
    fun setup() {
        mockkStatic("com.sample.android.tmdb.util.ContextExtKt")
        every {
            context.isNetworkAvailable()
        } returns true
    }

    @Test
    fun `test Api Succeeds`() {
        testCoroutineRule.runBlockingTest {
            `when`(tvShowApi.trendingTVSeries()).thenReturn(ItemWrapper(emptyList()))
            `when`(tvShowApi.popularTVSeries()).thenReturn(ItemWrapper(emptyList()))
            `when`(tvShowApi.onTheAirTVSeries()).thenReturn(ItemWrapper(emptyList()))
            `when`(tvShowApi.topRatedTVSeries()).thenReturn(ItemWrapper(emptyList()))
            `when`(tvShowApi.airingTodayTVSeries()).thenReturn(ItemWrapper(emptyList()))

            val repository = TVShowFeedRepository(context, Dispatchers.Main, tvShowApi)

            assertThat(repository.result.first(), `is`(ViewState.Loading))

            val result = (repository.result.last() as ViewState.Success).data

            assertThat(result[0].feeds, `is`(emptyList()))
            assertThat(result[1].feeds, `is`(emptyList()))
            assertThat(result[2].feeds, `is`(emptyList()))
            assertThat(result[3].feeds, `is`(emptyList()))
            assertThat(result[4].feeds, `is`(emptyList()))
        }
    }

    @Test
    fun `test Fetch trending tvShows Fails`() {
        val errorMsg = "error message"
        `when`(context.getString(anyInt())).thenReturn(errorMsg)


        testCoroutineRule.runBlockingTest {
            `when`(tvShowApi.trendingTVSeries()).thenThrow(RuntimeException(errorMsg))

            val repository = TVShowFeedRepository(context, Dispatchers.Main, tvShowApi)

            assertThat(repository.result.first(), `is`(ViewState.Loading))
            assertThat(repository.result.last(), `is`(ViewState.Error(errorMsg)))
        }
    }

    @Test
    fun `test Fetch popular tvShows Fails`() {
        val errorMsg = "error message"
        `when`(context.getString(anyInt())).thenReturn(errorMsg)

        testCoroutineRule.runBlockingTest {
            `when`(tvShowApi.popularTVSeries()).thenThrow(RuntimeException(errorMsg))

            val repository = TVShowFeedRepository(context, Dispatchers.Main, tvShowApi)

            assertThat(repository.result.first(), `is`(ViewState.Loading))
            assertThat(repository.result.last(), `is`(ViewState.Error(errorMsg)))
        }
    }

    @Test
    fun `test Fetch latest tvShows Fails`() {
        val errorMsg = "error message"
        `when`(context.getString(anyInt())).thenReturn(errorMsg)

        testCoroutineRule.runBlockingTest {
            `when`(tvShowApi.onTheAirTVSeries()).thenThrow(RuntimeException(errorMsg))

            val repository = TVShowFeedRepository(context, Dispatchers.Main, tvShowApi)

            assertThat(repository.result.first(), `is`(ViewState.Loading))
            assertThat(repository.result.last(), `is`(ViewState.Error(errorMsg)))
        }
    }

    @Test
    fun `test Fetch topRated tvShows Fails`() {
        val errorMsg = "error message"
        `when`(context.getString(anyInt())).thenReturn(errorMsg)

        testCoroutineRule.runBlockingTest {
            `when`(tvShowApi.topRatedTVSeries()).thenThrow(RuntimeException(errorMsg))

            val repository = TVShowFeedRepository(context, Dispatchers.Main, tvShowApi)

            assertThat(repository.result.first(), `is`(ViewState.Loading))
            assertThat(repository.result.last(), `is`(ViewState.Error(errorMsg)))
        }
    }
}