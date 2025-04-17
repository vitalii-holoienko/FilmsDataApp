package com.example.filmsdataapp.data.repository

import android.util.Log
import com.example.filmsdataapp.BuildConfig
import com.example.filmsdataapp.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class MovieRepositoryImpl() : MovieRepository {
    private val client = OkHttpClient()

    override suspend fun getMostPopularMovies(): String = withContext(Dispatchers.IO) {
        try {
            val request = Request.Builder()
                .url("https://imdb236.p.rapidapi.com/imdb/top250-movies")
                .get()
                .addHeader("x-rapidapi-key", BuildConfig.RAPID_API_KEY)
                .addHeader("x-rapidapi-host", BuildConfig.RAPID_API_HOST)
                .build()

            client.newCall(request).execute().use { response ->
                val bodyStr = response.body?.string() ?: ""
                Log.d("TEKKEN", "Code: ${response.code}, Body: $bodyStr")

                if (!response.isSuccessful) {
                    return@use ""
                }
                return@use bodyStr
            }
        } catch (e: Exception) {
            Log.e("TEKKEN", "Exception: ${e.message}", e)
            return@withContext ""
        }
    }
}