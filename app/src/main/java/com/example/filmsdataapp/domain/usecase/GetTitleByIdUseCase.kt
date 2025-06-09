package com.example.filmsdataapp.domain.usecase

import com.example.filmsdataapp.domain.model.Title
import com.example.filmsdataapp.domain.repository.MoviesRepository
import com.example.filmsdataapp.domain.repository.TitleRepository

class GetTitleById(
    private val repository: MoviesRepository
) {
    suspend operator fun invoke(id:String): Title {
        return repository.getTitleById(id)
    }
}

