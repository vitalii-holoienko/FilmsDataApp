package com.example.filmsdataapp.data.repository

import Movie
import android.util.Log
import com.example.filmsdataapp.data.network.NetworkHelper
import com.example.filmsdataapp.data.network.NetworkHelper.makeRequest
import com.example.filmsdataapp.domain.repository.MoviesRepository
import com.example.filmsdataapp.domain.repository.TitleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import okhttp3.OkHttpClient
import okhttp3.Request

class MoviesRepositoryImpl() : MoviesRepository{
    private val client = OkHttpClient()

    override suspend fun getMostPopularMovies(): List<Movie> = withContext(Dispatchers.IO) {
        val json = Json {
            ignoreUnknownKeys = true
        }
        val jsonString = makeRequest("https://imdb236.p.rapidapi.com/imdb/most-popular-movies", 1)
        json.decodeFromString(jsonString)

    }

    override suspend fun getComingSoonMovies(): List<Movie> = withContext(Dispatchers.IO) {
        val result = mutableListOf<Movie>()

        val idsJson = makeRequest(
            "https://imdb232.p.rapidapi.com/api/title/get-coming-soon?limit=20&comingSoonType=MOVIE",
            2
        )
        val ids = Json.parseToJsonElement(idsJson).jsonObject["data"]!!
            .jsonObject["comingSoon"]!!
            .jsonObject["edges"]!!
            .jsonArray.mapNotNull { it.jsonObject["node"]?.jsonObject?.get("id")?.jsonPrimitive?.content }
        val json = Json { ignoreUnknownKeys = true }

        ids.forEach { id ->
            try {
                val movieString = getMovieById(id)
                val movie = json.decodeFromString<Movie>(movieString)
                result.add(movie)
            } catch (e: Exception) {
                Log.e("MOVIE_FETCH", "Error $id: ${e.message}")
            }
        }

        return@withContext result
    }

    override suspend fun getCurrentlyTrendingMovies(): List<Movie> = withContext(Dispatchers.IO){

        val json = Json {
            ignoreUnknownKeys = true
        }
        val jsonString = makeRequest("https://imdb236.p.rapidapi.com/imdb/most-popular-movies", 1)
        json.decodeFromString(jsonString)
    }

    override suspend fun getMoviesWithAppliedFilter(): String {
        return ""
    }

    override suspend fun getMovieById(id:String): String = withContext(Dispatchers.IO){
        return@withContext makeRequest("https://imdb236.p.rapidapi.com/imdb/${id}",1)

    }
}