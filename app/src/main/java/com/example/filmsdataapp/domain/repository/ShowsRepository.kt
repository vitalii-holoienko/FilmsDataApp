package com.example.filmsdataapp.domain.repository

interface MovieRepository {
    suspend fun getMostPopularMovies(): String
}