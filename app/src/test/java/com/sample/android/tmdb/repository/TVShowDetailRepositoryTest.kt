package com.sample.android.tmdb.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.android.tmdb.TestRxJavaRule
import com.sample.android.tmdb.domain.Cast
import com.sample.android.tmdb.domain.CreditWrapper
import com.sample.android.tmdb.domain.Video
import com.sample.android.tmdb.domain.VideoWrapper
import com.sample.android.tmdb.network.TVShowApi
import io.reactivex.Single
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TVShowDetailRepositoryTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var rxJavaRule: TestRule = TestRxJavaRule()

    @Mock
    private lateinit var tvShowApi: TVShowApi

    @Test
    fun loadTrailersAndCredits() {
        val trailers = VideoWrapper(listOf(Video("id", "", "", "", "")))
        val creditWrapper = CreditWrapper(listOf(Cast("", "", null, 1)), listOf())
        `when`(tvShowApi.tvTrailers(anyInt())).thenReturn(Single.just(trailers))
        `when`(tvShowApi.tvCredit(anyInt())).thenReturn(Single.just(creditWrapper))

        val repository = TVShowDetailRepositoryImpl(tvShowApi)

        val expectedTrailers = Single.just(trailers).blockingGet()
        Assert.assertThat(
            repository.getTVShowTrailers(anyInt()).blockingGet(),
            `is`(expectedTrailers)
        )

        val expectedCredit = Single.just(creditWrapper).blockingGet()
        Assert.assertThat(
            repository.getTVShowCredit(anyInt()).blockingGet(), `is`(expectedCredit)
        )
    }

    @Test
    fun errorLoadTrailers() {
        val error = "errorMsg"
        `when`(tvShowApi.tvTrailers(anyInt())).thenReturn(Single.error(Exception(error)))

        val repository = TVShowDetailRepositoryImpl(tvShowApi)
        repository.getTVShowTrailers(anyInt()).test().assertErrorMessage(error)
    }

    @Test
    fun errorLoadCredits() {
        val error = "errorMsg"
        `when`(tvShowApi.tvCredit(anyInt())).thenReturn(Single.error(Exception(error)))

        val repository = TVShowDetailRepositoryImpl(tvShowApi)
        repository.getTVShowCredit(anyInt()).test().assertErrorMessage(error)
    }
}