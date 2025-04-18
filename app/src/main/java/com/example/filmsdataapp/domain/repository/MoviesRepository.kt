package com.example.filmsdataapp.domain.repository

interface MoviesRepository {
    suspend fun getMostPopularMovies(): String
    suspend fun getComingSoonMovies(): String
    suspend fun getCurrentlyTrendingMovies(): String
    suspend fun getMoviesWithAppliedFilter(): String

}