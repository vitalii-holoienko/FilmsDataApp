package com.example.filmsdataapp.data.repository

import TVShow
import com.example.filmsdataapp.data.network.NetworkHelper
import com.example.filmsdataapp.domain.repository.TVShowsRepository
import com.example.filmsdataapp.domain.repository.TitleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class TVShowsRepositoryImpl : TVShowsRepository{
    override suspend fun getMostPopularTVShows(): List<TVShow> = withContext(Dispatchers.IO) {
        val json = Json {
            ignoreUnknownKeys = true
        }
        val jsonString =
            NetworkHelper.makeRequest("https://imdb236.p.rapidapi.com/imdb/top250-tv", 1)
        json.decodeFromString(jsonString)

    }
}