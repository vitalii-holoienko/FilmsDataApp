package com.example.filmsdataapp.data.repository

import TVShow
import android.util.Log
import com.example.filmsdataapp.BuildConfig
import com.example.filmsdataapp.data.network.NetworkHelper
import com.example.filmsdataapp.data.remote.NetworkService
import com.example.filmsdataapp.data.remote.api.TVShowApi
import com.example.filmsdataapp.data.remote.api.TitleApi
import com.example.filmsdataapp.domain.model.Title
import com.example.filmsdataapp.domain.repository.TVShowsRepository
import com.example.filmsdataapp.domain.repository.TitleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.IOException

class TVShowsRepositoryImpl : TVShowsRepository{
    override suspend fun getMostPopularTVShows(): List<Title> = withContext(Dispatchers.IO) {
        val api = NetworkService.getInstance("https://imdb236.p.rapidapi.com/").create(TVShowApi::class.java)
        try{
            api.getMostPopularTVShows(BuildConfig.RAPID_API_KEY, BuildConfig.RAPID_API_HOST_ONE)
        } catch (ex : IOException){
            Log.e("DEBUG", "No internet connection")
            emptyList()
        } catch (ex : Exception){
            Log.e("DEBUG", "unexpected error")
            emptyList()
        }
    }
}