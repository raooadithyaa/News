package com.example.news

import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.*
import retrofit2.*

class DataCreation(private var key: String, private val newsApi: NewsApi) {

    suspend fun getData(prevDate: String, current: String): List<FinalData>? =
        withContext(Dispatchers.IO) {
            val response =
                newsApi.getData("apple", prevDate, current, "popularity", key).execute()
            if (response.isSuccessful) {
                return@withContext response.body()?.articles?.map {
                    FinalData(
                        it.title,
                        it.url,
                        it.urlToImage
                    )
                }
            } else {
                return@withContext ArrayList()
            }
        }

    //write unit test for this function
}
