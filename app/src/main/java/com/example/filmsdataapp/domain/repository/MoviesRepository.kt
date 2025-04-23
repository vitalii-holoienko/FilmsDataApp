package com.example.filmsdataapp.domain.repository

import Movie

interface MoviesRepository {
    suspend fun getMostPopularMovies(): List<Movie>
    suspend fun getComingSoonMovies(): List<Movie>
    suspend fun getCurrentlyTrendingMovies(): List<Movie>
    suspend fun getMoviesWithAppliedFilter(): String
    suspend fun getMovieById(id:String) : String

}