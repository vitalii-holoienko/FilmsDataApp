package com.example.filmsdataapp.data.repository

import TVShow
import com.example.filmsdataapp.data.network.NetworkHelper
import com.example.filmsdataapp.domain.model.Title
import com.example.filmsdataapp.domain.repository.TVShowsRepository
import com.example.filmsdataapp.domain.repository.TitleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class TVShowsRepositoryImpl : TVShowsRepository{
    override suspend fun getMostPopularTVShows(): List<Title> = withContext(Dispatchers.IO) {
        val json = Json {
            ignoreUnknownKeys = true
        }
        val jsonString = NetworkHelper.makeRequest("https://imdb236.p.rapidapi.com/api/imdb/top250-tv", 1)
            ?: return@withContext emptyList()

        if (jsonString.isBlank()) return@withContext emptyList()

        json.decodeFromString(jsonString)
    }
}