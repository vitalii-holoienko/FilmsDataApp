package com.example.filmsdataapp.data.repository

import com.example.filmsdataapp.data.network.NetworkHelper
import com.example.filmsdataapp.domain.model.Actor
import com.example.filmsdataapp.domain.model.ActorResponse
import com.example.filmsdataapp.domain.repository.ActorsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class ActorsRepositoryImpl : ActorsRepository {
    override suspend fun getActors(): List<Actor> = withContext(Dispatchers.IO) {
        //TODO logic
        val jsonString = NetworkHelper.makeRequest("https://moviesdatabase.p.rapidapi.com/actors?page=1&limit=10", 3)
        val json = Json { ignoreUnknownKeys = true }

        val jsonElement = Json.parseToJsonElement(jsonString)
        val nconstList = jsonElement
            .jsonObject["results"]!!
            .jsonArray
            .mapNotNull { it.jsonObject["nconst"]?.jsonPrimitive?.content }
        val result = mutableListOf<Actor>()
        nconstList.forEach {
            val js = NetworkHelper.makeRequest("https://imdb-com.p.rapidapi.com/actor/get-bio?nconst=${it}", 4)
            val actorResponse = json.decodeFromString<ActorResponse>(js)
            val actor = actorResponse.data!!.name
            result.add(actor!!)
        }
        return@withContext result
    }

    override suspend fun getActorBioById(id:String): String = withContext(Dispatchers.IO){
        NetworkHelper.makeRequest("https://imdb232.p.rapidapi.com/api/actors/get-bio?nm=${id}", 2)
    }

    override suspend fun getActorImagesById(id:String): String = withContext(Dispatchers.IO) {
        NetworkHelper.makeRequest("https://imdb232.p.rapidapi.com/api/actors/get-images?nm=${id}&limit=25", 2)
    }
}