package com.example.filmsdataapp.data.network

import android.util.Log
import com.example.filmsdataapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request

object NetworkHelper {
    private val client = OkHttpClient()

    fun makeRequest(url: String, hostFlag: Int): String? {
        val host = when (hostFlag) {
            1 -> BuildConfig.RAPID_API_HOST_ONE
            2 -> BuildConfig.RAPID_API_HOST_TWO
            3 -> BuildConfig.RAPID_API_HOST_THREE
            4 -> BuildConfig.RAPID_API_HOST_FOUR
            else -> throw IllegalArgumentException("Invalid hostFlag: $hostFlag")
        }

        return try {
            val request = Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-key", BuildConfig.RAPID_API_KEY)
                .addHeader("x-rapidapi-host", host)
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    println("Network error: ${response.code}")
                    Log.d("TEKKEN",response.code.toString() )
                    return null
                }
                response.body?.string()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}