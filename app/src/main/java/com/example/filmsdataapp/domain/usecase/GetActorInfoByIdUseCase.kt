package com.example.filmsdataapp.domain.usecase

import com.example.filmsdataapp.domain.model.ActorInfo
import com.example.filmsdataapp.domain.repository.ActorsRepository

class GetActorInfoByIdUseCase(
    private val repository: ActorsRepository
) {
    suspend operator fun invoke(id:String): ActorInfo {
        return repository.getActorOverviewById(id)
    }
}

