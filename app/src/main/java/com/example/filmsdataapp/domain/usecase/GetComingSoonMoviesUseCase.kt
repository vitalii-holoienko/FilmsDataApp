package com.example.filmsdataapp.domain.usecase


import Movie
import com.example.filmsdataapp.domain.repository.MoviesRepository

class GetComingSoonMoviesUseCase(
    private val repository: MoviesRepository
) {
    suspend operator fun invoke(): List<Movie> {
        return repository.getComingSoonMovies()
    }
}


