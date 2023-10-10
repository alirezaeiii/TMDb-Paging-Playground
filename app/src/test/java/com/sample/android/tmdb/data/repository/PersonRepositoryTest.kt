package com.sample.android.tmdb.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.android.tmdb.TestRxJavaRule
import com.sample.android.tmdb.data.response.PersonResponse
import com.sample.android.tmdb.data.network.PersonService
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
class PersonRepositoryTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var rxJavaRule: TestRule = TestRxJavaRule()

    @Mock
    private lateinit var personApi: PersonService

    @Test
    fun loadPerson() {
        val personId = 12
        val knownAs1 = "Ali"
        val knownAs2 = "Harry"
        val person = PersonResponse(
            null, null, personId, arrayOf(knownAs1, knownAs2),
            "biography", "place"
        )
        `when`(personApi.getPerson(anyInt())).thenReturn(Single.just(person))

        val repository = PersonRepositoryImpl(personApi)
        assertThat(repository.getPerson(anyInt()).blockingGet().id, `is`(personId))
        assertThat(repository.getPerson(anyInt()).blockingGet().alsoKnowAs, `is`(arrayOf(knownAs1, knownAs2)))
    }

    @Test
    fun errorLoadPerson() {
        val error = "errorMsg"
        `when`(personApi.getPerson(anyInt())).thenReturn(Single.error(Exception(error)))

        val repository = PersonRepositoryImpl(personApi)
        repository.getPerson(anyInt()).test().assertErrorMessage(error)
    }
}