package com.example.filmsdataapp.data.repository

import Movie
import android.util.Log
import com.example.filmsdataapp.BuildConfig
import com.example.filmsdataapp.data.network.NetworkHelper
import com.example.filmsdataapp.data.network.NetworkHelper.makeRequest
import com.example.filmsdataapp.data.remote.NetworkService
import com.example.filmsdataapp.data.remote.api.TVShowApi
import com.example.filmsdataapp.data.remote.api.TitleApi
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
import retrofit2.HttpException
import java.io.IOException

class MoviesRepositoryImpl : MoviesRepository {

    override suspend fun getMostPopularMovies(): List<Title> = withContext(Dispatchers.IO) {
        try{
            val api = NetworkService.getInstance("https://imdb236.p.rapidapi.com/").create(TitleApi::class.java)
            api.getTop250Movies(BuildConfig.RAPID_API_KEY, BuildConfig.RAPID_API_HOST_ONE)
        } catch (ex : IOException){
            Log.d("API_ERROR", "IOException error: ${ex.message}")
            emptyList()
        } catch (ex : Exception){
            Log.d("API_ERROR", "Unexpected error: ${ex.message}")
            emptyList()
        }
    }

    override suspend fun getComingSoonMovies(): List<Title> = withContext(Dispatchers.IO) {
        val result = mutableListOf<Title>()
        val api = NetworkService.getInstance("https://imdb232.p.rapidapi.com/").create(TitleApi::class.java)
        try {
            val r = api.getComingSoonMoviesRaw(
                apiKey = BuildConfig.RAPID_API_KEY,
                apiHost = BuildConfig.RAPID_API_HOST_TWO
            )
            Log.d("API_DEBUG", "Response received: ${r.data.comingSoon.edges.size}")
            val ids = r.data.comingSoon.edges.take(30).mapNotNull { it.node?.id }
            Log.d("API_ERROR", ids.size.toString())
            ids.forEach { id ->
                try {
                    val title = getTitleById(id)
                    result.add(title)
                } catch (e: Exception) {
                    Log.e("MOVIE_FETCH", "Error loading title $id: ${e.message}")
                }
            }

            return@withContext result
        } catch (e: HttpException) {
            Log.d("API_ERROR", "Http error ${e.code()}: ${e.message()}")
            return@withContext emptyList()
        } catch (e: Exception) {
            Log.d("API_ERROR", "Unexpected error: ${e.message}")
            return@withContext emptyList()
        }


    }

    override suspend fun getCurrentlyTrendingMovies(): List<Title> = withContext(Dispatchers.IO) {
        val api = NetworkService.getInstance("https://imdb236.p.rapidapi.com/").create(TitleApi::class.java)
        try{
            api.getCurrentlyTrendingMovies(BuildConfig.RAPID_API_KEY, BuildConfig.RAPID_API_HOST_ONE)
        } catch (ex : IOException){
            Log.e("DEBUG", "No internet connection")
            emptyList()
        } catch (ex : Exception){
            Log.d("API_ERROR", "Unexpected error: ${ex.message}")
            emptyList()
        }

    }

    override suspend fun getTitleById(id: String): Title = withContext(Dispatchers.IO) {
        val api = NetworkService.getInstance("https://imdb236.p.rapidapi.com/").create(TitleApi::class.java)
        try{
            api.getMovieById(id, BuildConfig.RAPID_API_KEY, BuildConfig.RAPID_API_HOST_ONE)
        } catch (ex : IOException){
            Log.e("DEBUG", "No internet connection")
            Title()
        } catch (ex : Exception){
            Log.d("API_ERROR", "Unexpected error: ${ex.message}")
            Title()
        }
    }


    override suspend fun getRatingById(id: String): String = withContext(Dispatchers.IO) {
        val api = NetworkService.getInstance("https://imdb236.p.rapidapi.com/").create(TitleApi::class.java)
        try{
            api.getRatingById(id, BuildConfig.RAPID_API_KEY, BuildConfig.RAPID_API_HOST_ONE)
        } catch (ex : IOException){
            Log.e("DEBUG", "No internet connection")
            ""
        } catch (ex : Exception){
            Log.d("API_ERROR", "Unexpected error: ${ex.message}")
            ""
        }


    }

    override suspend fun getTop250Movies(): List<Title> = withContext(Dispatchers.IO) {
        val api = NetworkService.getInstance("https://imdb236.p.rapidapi.com/").create(TitleApi::class.java)
        try{
            api.getTop250Movies(BuildConfig.RAPID_API_KEY, BuildConfig.RAPID_API_HOST_ONE)
        } catch (ex : IOException){
            Log.e("DEBUG", "No internet connection")
            emptyList()
        } catch (ex : Exception){
            Log.d("API_ERROR", "Unexpected error: ${ex.message}")
            emptyList()
        }
    }


}