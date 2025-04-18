package com.example.filmsdataapp.data.repository

import com.example.filmsdataapp.data.network.NetworkHelper
import com.example.filmsdataapp.domain.repository.ActorsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ActorsRepositoryImpl : ActorsRepository {
    override suspend fun getActors(): String = withContext(Dispatchers.IO) {
        //TODO logic
        NetworkHelper.makeRequest("https://moviesdatabase.p.rapidapi.com/actors?page=1&limit=10", 3)
    }

    override suspend fun getActorBioById(id:String): String = withContext(Dispatchers.IO){
        NetworkHelper.makeRequest("https://imdb232.p.rapidapi.com/api/actors/get-bio?nm=${id}", 2)
    }

    override suspend fun getActorImagesById(id:String): String = withContext(Dispatchers.IO) {
        NetworkHelper.makeRequest("https://imdb232.p.rapidapi.com/api/actors/get-images?nm=${id}&limit=25", 2)
    }
}