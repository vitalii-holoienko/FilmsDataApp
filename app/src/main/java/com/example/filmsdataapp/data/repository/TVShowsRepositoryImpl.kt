package com.example.filmsdataapp.data.repository

import com.example.filmsdataapp.data.network.NetworkHelper
import com.example.filmsdataapp.domain.repository.TVShowsRepository
import com.example.filmsdataapp.domain.repository.TitleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TVShowsRepositoryImpl : TVShowsRepository{
    override suspend fun getMostPopularTVShows(): String = withContext(Dispatchers.IO) {
        NetworkHelper.makeRequest("https://imdb236.p.rapidapi.com/imdb/top250-tv", 1)
    }
}