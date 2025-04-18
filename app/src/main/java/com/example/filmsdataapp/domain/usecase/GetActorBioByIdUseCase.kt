package com.example.filmsdataapp.domain.usecase

import com.example.filmsdataapp.domain.repository.ActorsRepository

class GetActorBioByIdUseCase(
    private val repository: ActorsRepository
) {
    suspend operator fun invoke(id:String): String {
        return repository.getActorBioById(id)
    }
}


