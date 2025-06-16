package com.example.filmsdataapp.data.repository

import Movie
import android.util.Log
import com.example.filmsdataapp.data.network.NetworkHelper
import com.example.filmsdataapp.data.network.NetworkHelper.makeRequest
import com.example.filmsdataapp.domain.model.Title
import com.example.filmsdataapp.domain.repository.MoviesRepository
import com.example.filmsdataapp.domain.repository.TitleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.double
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import okhttp3.OkHttpClient
import okhttp3.Request

class MoviesRepositoryImpl : MoviesRepository {

    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun getMostPopularMovies(): List<Title> = withContext(Dispatchers.IO) {
        val jsonString = makeRequest("https://imdb236.p.rapidapi.com/api/imdb/top250-movies", 1)
            ?: throw IllegalStateException("Empty response from getMostPopularMovies")

        json.decodeFromString(jsonString)
    }

    override suspend fun getComingSoonMovies(): List<Title> = withContext(Dispatchers.IO) {
        val result = mutableListOf<Title>()

        val idsJson = makeRequest(
            "https://imdb232.p.rapidapi.com/api/title/get-coming-soon?limit=100&comingSoonType=MOVIE",
            2
        ) ?: throw IllegalStateException("Empty response from getComingSoonMovies")

        val ids = try {
            Json.parseToJsonElement(idsJson).jsonObject["data"]!!
                .jsonObject["comingSoon"]!!
                .jsonObject["edges"]!!
                .jsonArray.mapNotNull {
                    it.jsonObject["node"]?.jsonObject?.get("id")?.jsonPrimitive?.content
                }
        } catch (e: Exception) {
            throw IllegalStateException("Failed to parse coming soon movie IDs", e)
        }

        ids.forEach { id ->
            try {
                val title = getTitleById(id)
                result.add(title)
            } catch (e: Exception) {
                Log.e("MOVIE_FETCH", "Error loading title $id: ${e.message}")
            }
        }

        return@withContext result
    }

    override suspend fun getCurrentlyTrendingMovies(): List<Title> = withContext(Dispatchers.IO) {
        val jsonString = makeRequest("https://imdb236.p.rapidapi.com/api/imdb/most-popular-movies", 1)
            ?: throw IllegalStateException("Empty response from getCurrentlyTrendingMovies")

        json.decodeFromString(jsonString)
    }

    override suspend fun getMoviesWithAppliedFilter(): String {
        return ""
    }

    override suspend fun getTitleById(id: String): Title = withContext(Dispatchers.IO) {
        val jsonString = makeRequest("https://imdb236.p.rapidapi.com/api/imdb/$id", 1)
            ?: throw IllegalStateException("Empty response from getTitleById for id = $id")

        try {
            json.decodeFromString<Title>(jsonString)
        } catch (e: Exception) {
            throw IllegalStateException("Failed to parse JSON for id = $id.\nRaw response: $jsonString", e)
        }
    }

    override suspend fun getRatingById(id: String): String = withContext(Dispatchers.IO) {
        makeRequest("https://imdb236.p.rapidapi.com/api/imdb/${id}/rating", 1) ?: ""
    }

    override suspend fun getTop250Movies(): List<Title> = withContext(Dispatchers.IO) {
        val jsonString = makeRequest("https://imdb236.p.rapidapi.com/api/imdb/top250-movies", 1)
            ?: throw IllegalStateException("Empty response from getTop250Movies")

        json.decodeFromString(jsonString)
    }
}