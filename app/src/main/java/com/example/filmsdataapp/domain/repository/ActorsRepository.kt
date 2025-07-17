package com.example.filmsdataapp.domain.repository

import com.example.filmsdataapp.domain.model.Actor
import com.example.filmsdataapp.domain.model.ActorInfo

interface ActorsRepository {
    suspend fun getActors() : List<Actor>
    suspend fun getActorOverviewById(id:String) : ActorInfo
}