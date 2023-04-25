package com.example.news

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
):java.io.Serializable