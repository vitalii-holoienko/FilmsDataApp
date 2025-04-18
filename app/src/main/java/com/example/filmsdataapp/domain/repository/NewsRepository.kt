package com.example.filmsdataapp.domain.repository

interface NewsRepository {
    suspend fun getNews(): String
}