package com.example.filmsdataapp.data.repository

import com.example.filmsdataapp.data.network.NetworkHelper
import com.example.filmsdataapp.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepositoryImpl : NewsRepository {
    override suspend fun getNews(): String = withContext(Dispatchers.IO) {
        NetworkHelper.makeRequest("https://imdb232.p.rapidapi.com/api/news/get-by-category?limit=25&category=TOP", 2)
    }
}