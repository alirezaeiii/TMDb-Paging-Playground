package com.sample.android.tmdb.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.android.tmdb.TestRxJavaRule
import com.sample.android.tmdb.domain.Cast
import com.sample.android.tmdb.domain.CreditWrapper
import com.sample.android.tmdb.domain.Video
import com.sample.android.tmdb.domain.VideoWrapper
import com.sample.android.tmdb.network.MovieApi
import io.reactivex.Single
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieDetailRepositoryTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var rxJavaRule: TestRule = TestRxJavaRule()

    @Mock
    private lateinit var movieApi: MovieApi

    @Test
    fun loadTrailersAndCredits() {
        val trailers = VideoWrapper(listOf(Video("id", "", "", "", "")))
        val creditWrapper = CreditWrapper(listOf(Cast("", "", null, 1)), listOf())
        `when`(movieApi.movieTrailers(anyInt())).thenReturn(Single.just(trailers))
        `when`(movieApi.movieCredit(anyInt())).thenReturn(Single.just(creditWrapper))

        val repository = MovieDetailRepositoryImpl(movieApi)

        assertThat(repository.getMovieTrailers(anyInt()).blockingGet(), `is`(trailers))
        assertThat(repository.getMovieCredit(anyInt()).blockingGet(), `is`(creditWrapper))
    }

    @Test
    fun errorLoadTrailers() {
        val error = "errorMsg"
        `when`(movieApi.movieTrailers(anyInt())).thenReturn(Single.error(Exception(error)))

        val repository = MovieDetailRepositoryImpl(movieApi)
        repository.getMovieTrailers(anyInt()).test().assertErrorMessage(error)
    }

    @Test
    fun errorLoadCredits() {
        val error = "errorMsg"
        `when`(movieApi.movieCredit(anyInt())).thenReturn(Single.error(Exception(error)))

        val repository = MovieDetailRepositoryImpl(movieApi)
        repository.getMovieCredit(anyInt()).test().assertErrorMessage(error)
    }
}