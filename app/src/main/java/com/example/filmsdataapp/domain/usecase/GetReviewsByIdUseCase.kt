package com.example.filmsdataapp.domain.usecase

import com.example.filmsdataapp.domain.repository.MoviesRepository
import com.example.filmsdataapp.domain.repository.TitleRepository

class GetReviewsByIdUseCase(
    private val repository: TitleRepository
) {
    suspend operator fun invoke(id:String): String {
        return repository.getReviewsById(id)
    }
}

