package com.sample.android.tmdb

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.android.tmdb.api.ItemApi
import com.sample.android.tmdb.repository.RemoteDataSource
import com.sample.android.tmdb.ui.person.PersonViewModel
import com.sample.android.tmdb.domain.Person
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.junit.*
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PersonViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var itemApi: ItemApi
    private lateinit var dataSource: RemoteDataSource

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        MockitoAnnotations.initMocks(this)

        dataSource = RemoteDataSource(itemApi)
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun loadPerson() {
        val personId = 12
        val knownAs1 = "Ali"
        val knownAs2 = "Harry"
        val person = Person(null ,null, personId, arrayOf(knownAs1, knownAs2),
                "biography", "place")

        val observableResponse = Observable.just(person)
        `when`(itemApi.person(anyInt())).thenReturn(observableResponse)

        val viewModel = PersonViewModel(dataSource, anyInt())

        with(viewModel) {
            assertTrue(isPersonDetailVisible.get())
            assertTrue(this.person.value?.id == personId)
            with(this.person.value?.alsoKnowAs!!) {
                assertTrue(size == 2)
                assertTrue(this[0] == knownAs1)
                assertTrue(this[1] == knownAs2)
            }
        }
    }

    @Test
    fun errorLoadPerson() {
        val observableResponse = Observable.error<Person>(Exception())
        `when`(itemApi.person(anyInt())).thenReturn(observableResponse)

        val viewModel = PersonViewModel(dataSource, anyInt())

        with(viewModel) {
            assertFalse(isPersonDetailVisible.get())
            Assert.assertThat(person.value, `is`(nullValue()))
        }
    }
}