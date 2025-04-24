package com.example.filmsdataapp.domain.repository

import TVShow

interface TVShowsRepository {
    suspend fun getMostPopularTVShows() : List<TVShow>
}