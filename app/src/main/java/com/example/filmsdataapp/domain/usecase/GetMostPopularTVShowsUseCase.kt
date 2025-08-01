package com.example.filmsdataapp.domain.usecase

import TVShow
import com.example.filmsdataapp.domain.model.Title
import com.example.filmsdataapp.domain.repository.MoviesRepository
import com.example.filmsdataapp.domain.repository.TVShowsRepository
import javax.inject.Inject

class GetMostPopularTVShowsUseCase @Inject constructor(
    private val repository: TVShowsRepository
) {
    suspend operator fun invoke(): List<Title> {
        return repository.getMostPopularTVShows()
    }
}
