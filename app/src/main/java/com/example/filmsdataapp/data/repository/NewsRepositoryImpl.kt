package com.example.filmsdataapp.data.repository

import com.example.filmsdataapp.BuildConfig
import com.example.filmsdataapp.data.network.NetworkHelper
import com.example.filmsdataapp.data.remote.NetworkService
import com.example.filmsdataapp.data.remote.api.NewsApi
import com.example.filmsdataapp.data.remote.api.TitleApi
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

        val api = NetworkService.getInstance("https://imdb232.p.rapidapi.com/").create(NewsApi::class.java)
        val rt = api.getNews(apiKey = BuildConfig.RAPID_API_KEY, apiHost = BuildConfig.RAPID_API_HOST_TWO)

        try {
            return@withContext rt.data.news.edges.map { it.node }
        } catch (e: Exception) {
            throw IllegalStateException("Failed to parse news JSON")
        }
    }
}