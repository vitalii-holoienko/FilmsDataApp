package com.example.filmsdataapp.data.repository

import android.util.Log
import com.example.filmsdataapp.data.network.NetworkHelper
import com.example.filmsdataapp.domain.model.Actor
import com.example.filmsdataapp.domain.model.ActorResponse
import com.example.filmsdataapp.domain.repository.ActorsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class ActorsRepositoryImpl : ActorsRepository {
    val result = mutableListOf<Actor>()
    val json = Json { ignoreUnknownKeys = true }
    override suspend fun getActors(): List<Actor> = withContext(Dispatchers.IO) {
        val result = mutableListOf<Actor>()

        coroutineScope {
            for (i in 1..5) {
                val jsonString = NetworkHelper.makeRequest("https://moviesdatabase.p.rapidapi.com/actors?page=${i}&limit=10", 3)
                val jsonElement = Json.parseToJsonElement(jsonString)

                val nconstList = jsonElement
                    .jsonObject["results"]!!
                    .jsonArray
                    .mapNotNull { it.jsonObject["nconst"]?.jsonPrimitive?.content }

                // Асинхронные запросы get-bio
                val deferredActors = nconstList.map { nconst ->
                    async {
                        try {
//                            val js = NetworkHelper.makeRequest("https://imdb-com.p.rapidapi.com/actor/get-bio?nconst=${nconst}", 4)
                            val js = getActorBioById(nconst)
                            val actorResponse = json.decodeFromString<ActorResponse>(js)
                            actorResponse.data?.name
                        } catch (e: Exception) {
                            Log.e("TEKKEN", "ERROR for  $nconst: ${e.message}")
                            null
                        }
                    }
                }

                val actors = deferredActors.awaitAll().filterNotNull()
                result.addAll(actors)
            }
        }

        return@withContext result
    }

    override suspend fun getActorBioById(id:String): String = withContext(Dispatchers.IO){
        NetworkHelper.makeRequest("https://imdb-com.p.rapidapi.com/actor/get-bio?nconst=${id}", 4)
    }

    override suspend fun getActorImagesById(id:String): String = withContext(Dispatchers.IO) {
        NetworkHelper.makeRequest("https://imdb232.p.rapidapi.com/api/actors/get-images?nm=${id}&limit=25", 2)
    }
}