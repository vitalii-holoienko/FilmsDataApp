package com.example.filmsdataapp.domain.repository

import TVShow
import com.example.filmsdataapp.domain.model.Title

interface TVShowsRepository {
    suspend fun getMostPopularTVShows() : List<Title>
}