package com.example.news

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class IntegrationTest {
    lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter


    @Before
    fun setUp() {
        val activityController = Robolectric.buildActivity(
            MainActivity::class.java
        ).setup()

        val activity = activityController.get()

        val fragment = activity
            .supportFragmentManager
            .fragments[0] as HomeFragment

        recyclerView = fragment.view!!.findViewById(R.id.recyclerview)

        adapter = recyclerView.adapter as MyAdapter
    }

    @Test
    fun checkView1() {
        adapter.updateNews(
            listOf(
                FinalData(
                    "hi","www.google.com","https://img.zonebourse.com/reuters/2023-03/2023-03-10T053622Z_1_LYNXMPEJ2904M_RTROPTP_3_NISSAN-ELECTRIC-ARIYA.JPG"),
                FinalData(
                    "hi","www.google.com","https://img.zonebourse.com/reuters/2023-03/2023-03-10T053622Z_1_LYNXMPEJ2904M_RTROPTP_3_NISSAN-ELECTRIC-ARIYA.JPG"))
        )
        recyclerView.measure(
            View.MeasureSpec.UNSPECIFIED,
            View.MeasureSpec.UNSPECIFIED
        )
        recyclerView.layout(0, 0, 1000, 1000)

        val viewHolder = recyclerView.findViewHolderForAdapterPosition(0)
        val res = viewHolder?.itemView?.findViewById<TextView>(R.id.title)

        Assert.assertEquals("hi", res?.text)
    }

    @Test
    fun checkView2() {
        adapter.updateNews(
            listOf(
                FinalData(
                    "hi","www.google.com","https://img.zonebourse.com/reuters/2023-03/2023-03-10T053622Z_1_LYNXMPEJ2904M_RTROPTP_3_NISSAN-ELECTRIC-ARIYA.JPG"),
                FinalData(
                    "bye","www.google.com","https://img.zonebourse.com/reuters/2023-03/2023-03-10T053622Z_1_LYNXMPEJ2904M_RTROPTP_3_NISSAN-ELECTRIC-ARIYA.JPG"))
        )
        recyclerView.measure(
            View.MeasureSpec.UNSPECIFIED,
            View.MeasureSpec.UNSPECIFIED
        )
        recyclerView.layout(0, 0, 1000, 1000)

        val viewHolder = recyclerView.findViewHolderForAdapterPosition(0 )
        val res = viewHolder?.itemView?.findViewById<TextView>(R.id.title)

        Assert.assertEquals("hi", res?.text)
    }

}