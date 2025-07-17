package com.example.filmsdataapp.data.remote.api

import com.example.filmsdataapp.domain.model.News
import com.example.filmsdataapp.domain.model.RootNews
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NewsApi {
    @GET("api/news/get-by-category")
    suspend fun getNews(
        @Query("limit") limit: Int = 50,
        @Query("category") category: String = "TOP",
        @Header("X-Rapidapi-Key") apiKey: String,
        @Header("X-Rapidapi-Host") apiHost: String
    ) : RootNews


}