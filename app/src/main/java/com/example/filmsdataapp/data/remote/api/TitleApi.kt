package com.example.filmsdataapp.data.remote.api

import Movie
import com.example.filmsdataapp.BuildConfig
import com.example.filmsdataapp.domain.model.ComingSoonResponse
import com.example.filmsdataapp.domain.model.FullResponse
import com.example.filmsdataapp.domain.model.Review
import com.example.filmsdataapp.domain.model.Title
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface TitleApi {

    @GET("api/title/get-coming-soon")
    suspend fun getComingSoonMoviesRaw(
        @Query("limit") limit: String = "10",
        @Query("comingSoonType") comingSoonType: String = "MOVIE",
        @Header("X-Rapidapi-Key") apiKey: String,
        @Header("X-Rapidapi-Host") apiHost: String
    ): ComingSoonResponse

    @GET("api/imdb/most-popular-movies")
    suspend fun getCurrentlyTrendingMovies(
        @Header("X-Rapidapi-Key") apiKey: String = BuildConfig.RAPID_API_KEY,
        @Header("X-Rapidapi-Host") apiHost: String
    ): List<Title>

    @GET("api/imdb/{id}/rating")
    suspend fun getRatingById(
        @Path("id") movieId: String,
        @Header("X-Rapidapi-Key") apiKey: String = BuildConfig.RAPID_API_KEY,
        @Header("X-Rapidapi-Host") apiHost: String
    ): String


    @GET("api/imdb/top250-movies")
    suspend fun getTop250Movies(
        @Header("X-Rapidapi-Key") apiKey: String = BuildConfig.RAPID_API_KEY,
        @Header("X-Rapidapi-Host") apiHost: String
    ): List<Title>


    @GET("api/imdb/{id}")
    suspend fun getMovieById(
        @Path("id") movieId: String,
        @Header("X-Rapidapi-Key") apiKey: String = BuildConfig.RAPID_API_KEY,
        @Header("X-Rapidapi-Host") apiHost: String
    ): Title


    @GET("api/imdb/top250-movies")
    suspend fun getMostPopularMovies(
        @Header("X-Rapidapi-Key") apiKey: String = BuildConfig.RAPID_API_KEY,
        @Header("X-Rapidapi-Host") apiHost: String
    ): List<Title>

    @GET("api/title/get-user-reviews")
    suspend fun getReviewsById(
        @Query("order") order: String = "DESC",
        @Query("spoiler") spoiler: String = "EXCLUDE",
        @Query("tt") tt : String,
        @Query("sortBy") sortBy: String = "HELPFULNESS_SCORE",
        @Header("X-Rapidapi-Key") apiKey: String = BuildConfig.RAPID_API_KEY,
        @Header("X-Rapidapi-Host") apiHost: String
    ) : FullResponse

    @GET("api/imdb/autocomplete")
    suspend fun searchTitle(
        @Query("query") query: String,
        @Header("X-Rapidapi-Key") apiKey: String = BuildConfig.RAPID_API_KEY,
        @Header("X-Rapidapi-Host") apiHost: String
    ): List<Title>









}