package com.example.filmsdataapp.domain.usecase

import TVShow
import com.example.filmsdataapp.domain.repository.MoviesRepository
import com.example.filmsdataapp.domain.repository.TVShowsRepository

class GetMostPopularTVShowsUseCase(
    private val repository: TVShowsRepository
) {
    suspend operator fun invoke(): List<TVShow> {
        return repository.getMostPopularTVShows()
    }
}
