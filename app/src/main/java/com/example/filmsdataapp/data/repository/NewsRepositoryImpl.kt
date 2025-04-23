package com.example.filmsdataapp.data.repository

import com.example.filmsdataapp.data.network.NetworkHelper
import com.example.filmsdataapp.domain.model.News
import com.example.filmsdataapp.domain.model.RootNews
import com.example.filmsdataapp.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class NewsRepositoryImpl : NewsRepository {
    override suspend fun getNews(): List<News> = withContext(Dispatchers.IO) {
        val json = Json {
            ignoreUnknownKeys = true
        }
        val jsonString =
            NetworkHelper.makeRequest("https://imdb232.p.rapidapi.com/api/news/get-by-category?limit=25&category=TOP", 2)
        val root = json.decodeFromString<RootNews>(jsonString)
        return@withContext root.data.news.edges.map { it.node }

    }
}