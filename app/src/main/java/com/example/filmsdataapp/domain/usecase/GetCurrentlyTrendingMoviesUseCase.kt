package com.example.filmsdataapp.domain.usecase

import com.example.filmsdataapp.domain.repository.MoviesRepository

class GetCurrentlyTrendingMoviesUseCase(
    private val repository: MoviesRepository
) {
    suspend operator fun invoke(): String {
        return repository.getCurrentlyTrendingMovies()
    }
}

