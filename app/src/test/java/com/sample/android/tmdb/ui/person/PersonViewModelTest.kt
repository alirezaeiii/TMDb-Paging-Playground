package com.sample.android.tmdb.ui.person

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.android.tmdb.TestRxJavaRule
import com.sample.android.tmdb.domain.Person
import com.sample.android.tmdb.network.PersonApi
import io.reactivex.Single
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PersonViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var rxJavaRule: TestRule = TestRxJavaRule()

    @Mock
    private lateinit var api: PersonApi

    @Test
    fun loadPerson() {
        val personId = 12
        val knownAs1 = "Ali"
        val knownAs2 = "Harry"
        val person = Person(null, null, personId, arrayOf(knownAs1, knownAs2),
                "biography", "place")

        val observableResponse = Single.just(person)
        `when`(api.getPerson(anyInt())).thenReturn(observableResponse)

        val viewModel = PersonViewModel(api, anyInt())

        viewModel.liveData.value?.let {
            assertTrue(it.id == personId)
            assertTrue(it.alsoKnowAs.size == 2)
            assertTrue(it.alsoKnowAs[0] == knownAs1)
            assertTrue(it.alsoKnowAs[1] == knownAs2)
        }
    }

    @Test
    fun errorLoadPerson() {
        `when`(api.getPerson(anyInt())).thenReturn(Single.error(Exception()))

        val viewModel = PersonViewModel(api, anyInt())

        with(viewModel) {
            assertThat(liveData.value, `is`(nullValue()))
        }
    }
}