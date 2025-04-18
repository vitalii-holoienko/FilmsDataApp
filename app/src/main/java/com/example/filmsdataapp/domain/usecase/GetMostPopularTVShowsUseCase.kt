package com.example.filmsdataapp.domain.usecase

import com.example.filmsdataapp.domain.repository.MoviesRepository
import com.example.filmsdataapp.domain.repository.TVShowsRepository

class GetMostPopularTVShowsUseCase(
    private val repository: TVShowsRepository
) {
    suspend operator fun invoke(): String {
        return repository.getMostPopularTVShows()
    }
}
