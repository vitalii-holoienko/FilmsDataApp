package com.example.filmsdataapp.domain.repository

interface ActorsRepository {
    suspend fun getActors() : String
    suspend fun getActorBioById(id:String) : String
    suspend fun getActorImagesById(id:String) : String
}