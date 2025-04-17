package com.example.filmsdataapp.domain.usecase

import com.example.filmsdataapp.domain.repository.MovieRepository

class GetMostPopularMoviesUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(): String {
        return repository.getMostPopularMovies()
    }
}