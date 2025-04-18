package com.example.filmsdataapp.data.repository

import android.util.Log
import com.example.filmsdataapp.BuildConfig
import com.example.filmsdataapp.data.network.NetworkHelper
import com.example.filmsdataapp.data.network.NetworkHelper.makeRequest
import com.example.filmsdataapp.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class MoviesRepositoryImpl() : MoviesRepository {
    private val client = OkHttpClient()

    override suspend fun getMostPopularMovies(): String = withContext(Dispatchers.IO) {
        makeRequest("https://imdb236.p.rapidapi.com/imdb/top250-movies", 1)
    }

    override suspend fun getComingSoonMovies(): String = withContext(Dispatchers.IO){
        makeRequest("https://imdb232.p.rapidapi.com/api/title/get-coming-soon?limit=20&comingSoonType=MOVIE", 1)
    }

    override suspend fun getCurrentlyTrendingMovies(): String = withContext(Dispatchers.IO){
        makeRequest("https://imdb236.p.rapidapi.com/imdb/most-popular-movies", 1)
    }

    override suspend fun getMoviesWithAppliedFilter(): String {
        return ""
    }




}