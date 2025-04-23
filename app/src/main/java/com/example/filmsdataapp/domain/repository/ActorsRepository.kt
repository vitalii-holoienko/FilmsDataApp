package com.example.filmsdataapp.domain.repository

import com.example.filmsdataapp.domain.model.Actor

interface ActorsRepository {
    suspend fun getActors() : List<Actor>
    suspend fun getActorBioById(id:String) : String
    suspend fun getActorImagesById(id:String) : String
}