package com.example.news

import junit.framework.TestCase.assertEquals
import org.junit.Test

class CircleTest {
    private val obj=Circle()
    @Test
    fun assertArea() {
        val res=obj.area(7.0)
        assertEquals(res,153.9380,0.0001)
    }

    @Test
    fun circumference() {
        val res=obj.circumference(7.9)
        assertEquals(res,49.6,0.1)

        val res2 = obj.circumference(10.0, 5.0)
        assertEquals(100.0, res2)
    }

}