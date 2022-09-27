package com.sample.android.tmdb.repository

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.android.tmdb.TestCoroutineRule
import com.sample.android.tmdb.domain.ItemWrapper
import com.sample.android.tmdb.network.MovieApi
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
class MovieFeedRepositoryTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var movieApi: MovieApi

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
            `when`(movieApi.trendingMovies()).thenReturn(ItemWrapper(emptyList()))
            `when`(movieApi.popularMovies()).thenReturn(ItemWrapper(emptyList()))
            `when`(movieApi.upcomingMovies()).thenReturn(ItemWrapper(emptyList()))
            `when`(movieApi.topRatedMovies()).thenReturn(ItemWrapper(emptyList()))
            `when`(movieApi.nowPlayingMovies()).thenReturn(ItemWrapper(emptyList()))

            val repository = MovieFeedRepository(context, Dispatchers.Main, movieApi)

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
    fun `test Fetch trending movies Fails`() {
        val errorMsg = "error message"
        `when`(context.getString(anyInt())).thenReturn(errorMsg)


        testCoroutineRule.runBlockingTest {
            `when`(movieApi.trendingMovies()).thenThrow(RuntimeException(errorMsg))

            val repository = MovieFeedRepository(context, Dispatchers.Main, movieApi)

            assertThat(repository.result.first(), `is`(ViewState.Loading))
            assertThat(repository.result.last(), `is`(ViewState.Error(errorMsg)))
        }
    }

    @Test
    fun `test Fetch popular movies Fails`() {
        val errorMsg = "error message"
        `when`(context.getString(anyInt())).thenReturn(errorMsg)


        testCoroutineRule.runBlockingTest {
            `when`(movieApi.popularMovies()).thenThrow(RuntimeException(errorMsg))

            val repository = MovieFeedRepository(context, Dispatchers.Main, movieApi)

            assertThat(repository.result.first(), `is`(ViewState.Loading))
            assertThat(repository.result.last(), `is`(ViewState.Error(errorMsg)))
        }
    }

    @Test
    fun `test Fetch latest movies Fails`() {
        val errorMsg = "error message"
        `when`(context.getString(anyInt())).thenReturn(errorMsg)


        testCoroutineRule.runBlockingTest {
            `when`(movieApi.upcomingMovies()).thenThrow(RuntimeException(errorMsg))

            val repository = MovieFeedRepository(context, Dispatchers.Main, movieApi)

            assertThat(repository.result.first(), `is`(ViewState.Loading))
            assertThat(repository.result.last(), `is`(ViewState.Error(errorMsg)))
        }
    }

    @Test
    fun `test Fetch topRated movies Fails`() {
        val errorMsg = "error message"
        `when`(context.getString(anyInt())).thenReturn(errorMsg)

        testCoroutineRule.runBlockingTest {
            `when`(movieApi.topRatedMovies()).thenThrow(RuntimeException(errorMsg))

            val repository = MovieFeedRepository(context, Dispatchers.Main, movieApi)

            assertThat(repository.result.first(), `is`(ViewState.Loading))
            assertThat(repository.result.last(), `is`(ViewState.Error(errorMsg)))
        }
    }
}