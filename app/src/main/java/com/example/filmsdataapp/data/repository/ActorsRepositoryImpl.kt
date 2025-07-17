package com.example.filmsdataapp.data.repository

import android.util.Log
import com.example.filmsdataapp.BuildConfig
import com.example.filmsdataapp.data.network.NetworkHelper
import com.example.filmsdataapp.data.remote.NetworkService
import com.example.filmsdataapp.data.remote.api.ActorApi
import com.example.filmsdataapp.data.remote.api.TitleApi
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
        val api = NetworkService.getInstance("https://imdb232.p.rapidapi.com/").create(ActorApi::class.java)
        val ar = api.getActors(apiKey = BuildConfig.RAPID_API_KEY, apiHost = BuildConfig.RAPID_API_HOST_TWO)
        return@withContext ar.data.topMeterNames.edges.map { it.node }
    }

    override suspend fun getActorOverviewById(id: String): ActorInfo = withContext(Dispatchers.IO) {
        val api = NetworkService.getInstance("https://imdb232.p.rapidapi.com/").create(ActorApi::class.java)
        val ar = api.getActorOverviewById(id = id, apiKey = BuildConfig.RAPID_API_KEY, apiHost = BuildConfig.RAPID_API_HOST_TWO)
        return@withContext ar.data.name
    }

}
