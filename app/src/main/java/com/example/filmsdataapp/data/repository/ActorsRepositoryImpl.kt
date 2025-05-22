package com.example.filmsdataapp.data.repository

import android.util.Log
import com.example.filmsdataapp.data.network.NetworkHelper
import com.example.filmsdataapp.domain.model.Actor
import com.example.filmsdataapp.domain.model.ActorDetailResponse
import com.example.filmsdataapp.domain.model.ActorInfo
import com.example.filmsdataapp.domain.model.ActorsResponse
import com.example.filmsdataapp.domain.repository.ActorsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class ActorsRepositoryImpl : ActorsRepository {
    override suspend fun getActors(): List<Actor> = withContext(Dispatchers.IO) {
        val json = Json {
            ignoreUnknownKeys = true
        }
        val jsonString = NetworkHelper.makeRequest(
            "https://imdb232.p.rapidapi.com/api/actors/get-most-popular?limit=100",
            2
        )

        val response = json.decodeFromString<ActorsResponse>(jsonString)
        return@withContext response.data.topMeterNames.edges.map { it.node }
    }
    override suspend fun getActorOverviewById(id: String): ActorInfo = withContext(Dispatchers.IO) {
        val json = Json {
            ignoreUnknownKeys = true // Игнорируем лишние поля
        }

        val jsonString = NetworkHelper.makeRequest(
            "https://imdb232.p.rapidapi.com/api/actors/get-overview?limit=25&nm=${id}", 2
        )

        val response = json.decodeFromString<ActorDetailResponse>(jsonString)

        return@withContext response.data.name // Это и есть ActorInfo
    }

    override suspend fun getActorImagesById(id:String): String = withContext(Dispatchers.IO) {
        NetworkHelper.makeRequest("https://imdb232.p.rapidapi.com/api/actors/get-images?nm=${id}&limit=25", 2)
    }
}