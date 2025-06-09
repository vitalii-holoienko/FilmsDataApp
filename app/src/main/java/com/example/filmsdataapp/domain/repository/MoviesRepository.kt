package com.example.filmsdataapp.domain.repository

import Movie
import com.example.filmsdataapp.domain.model.Title

interface MoviesRepository {
    suspend fun getMostPopularMovies(): List<Title>
    suspend fun getComingSoonMovies(): List<Title>
    suspend fun getCurrentlyTrendingMovies(): List<Title>
    suspend fun getMoviesWithAppliedFilter(): String
    suspend fun getTitleById(id:String) : Title
    suspend fun getRatingById(id:String) : String

    suspend fun getTop250Movies() : List<Title>

}