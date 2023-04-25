package com.example.news


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

   //@GET("v2/everything?q=apple&from=2023-03-05&to=2023-03-05&sortBy=popularity&apiKey=12b04bcc7a9b4e3d9b2286ff1994e619")
    @GET("v2/everything")
    fun getData(
       @Query("q") q: String,
       @Query("from") fromDate: String,
       @Query("to") toDate: String,
       @Query("sortBy") sort: String,
       @Query("apiKey") key: String,
   ) : Call<News>

}