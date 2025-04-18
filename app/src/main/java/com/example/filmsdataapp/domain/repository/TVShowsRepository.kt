package com.example.filmsdataapp.domain.repository

interface TVShowsRepository {
    suspend fun getMostPopularTVShows() : String
}