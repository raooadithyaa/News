package com.example.news

import android.os.Bundle
import kotlinx.coroutines.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.internal.runners.JUnit38ClassRunner
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import org.robolectric.Robolectric
import retrofit2.Call
import retrofit2.Response

@RunWith(JUnit4::class)
class HomeFragmentTest {

    lateinit var dataCreation :DataCreation

    @Mock
    lateinit var newsApi: NewsApi

    @Mock
    lateinit var call: Call<News>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        val mockResponse = Response.success(News(emptyList(), "200", 0))
        whenever(newsApi.getData(any(), any(), any(), any(), any())).thenReturn(call)
        whenever(call.execute()).thenReturn(mockResponse)
        dataCreation= DataCreation("12b04bcc7a9b4e3d9b2286ff1994e619",  newsApi)

    }

    @Test
    fun dataCheck()= runBlocking{
        val res=dataCreation.getData("2023-03-08","2023-03-09")
        assertEquals(0, res?.size)
    }

}