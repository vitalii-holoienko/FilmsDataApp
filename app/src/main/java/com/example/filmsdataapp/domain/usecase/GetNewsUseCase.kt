package com.example.filmsdataapp.domain.usecase

import com.example.filmsdataapp.domain.model.News
import com.example.filmsdataapp.domain.repository.MoviesRepository
import com.example.filmsdataapp.domain.repository.NewsRepository

class GetNewsUseCase(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(): List<News> {
        return repository.getNews()
    }
}

