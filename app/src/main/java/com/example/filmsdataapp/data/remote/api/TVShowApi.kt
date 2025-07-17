package com.example.filmsdataapp.data.remote.api

import com.example.filmsdataapp.domain.model.Title
import retrofit2.http.GET
import retrofit2.http.Header

interface TVShowApi {
    @GET("api/imdb/top250-tv")
    suspend fun getMostPopularTVShows(
        @Header("X-Rapidapi-Key") apiKey: String,
        @Header("X-Rapidapi-Host") apiHost: String
    ) : List<Title>
}