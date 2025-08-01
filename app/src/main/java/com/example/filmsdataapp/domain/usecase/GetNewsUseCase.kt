package com.example.filmsdataapp.domain.usecase

import android.util.Log
import com.example.filmsdataapp.domain.model.News
import com.example.filmsdataapp.domain.repository.MoviesRepository
import com.example.filmsdataapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(): List<News> {
        Log.d("TEKKEN", "ADSADSAD")
        return repository.getNews()
    }
}

