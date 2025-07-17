package com.example.filmsdataapp.data.remote.api

import com.example.filmsdataapp.domain.model.ActorDetailResponse
import com.example.filmsdataapp.domain.model.ActorsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ActorApi{
    @GET("api/actors/get-most-popular")
    suspend fun getActors(
        @Query("limit") limit : Int = 100,
        @Header("X-Rapidapi-Key") apiKey: String,
        @Header("X-Rapidapi-Host") apiHost: String
    ) : ActorsResponse

    @GET("api/actors/get-overview")
    suspend fun getActorOverviewById(
        @Query("nm") id : String,
        @Query("limit") limit : Int = 25,
        @Header("X-Rapidapi-Key") apiKey: String,
        @Header("X-Rapidapi-Host") apiHost: String
    ) : ActorDetailResponse
}

