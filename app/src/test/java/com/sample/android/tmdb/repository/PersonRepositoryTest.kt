package com.sample.android.tmdb.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.android.tmdb.TestRxJavaRule
import com.sample.android.tmdb.domain.Person
import com.sample.android.tmdb.network.PersonApi
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
    private lateinit var personApi: PersonApi

    @Test
    fun loadPerson() {
        val personId = 12
        val knownAs1 = "Ali"
        val knownAs2 = "Harry"
        val person = Person(
            null, null, personId, arrayOf(knownAs1, knownAs2),
            "biography", "place"
        )
        `when`(personApi.getPerson(anyInt())).thenReturn(Single.just(person))

        val repository = PersonRepositoryImpl(personApi)

        val expectedTrailers = Single.just(person).blockingGet()
        assertThat(repository.getPerson(anyInt()).blockingGet(), `is`(expectedTrailers))
    }

    @Test
    fun errorLoadPerson() {
        val error = "errorMsg"
        `when`(personApi.getPerson(anyInt())).thenReturn(Single.error(Exception(error)))

        val repository = PersonRepositoryImpl(personApi)
        repository.getPerson(anyInt()).test().assertErrorMessage(error)
    }
}