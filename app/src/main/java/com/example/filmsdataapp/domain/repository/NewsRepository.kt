package com.example.filmsdataapp.domain.repository

import com.example.filmsdataapp.domain.model.News

interface NewsRepository {
    suspend fun getNews(): List<News>
}