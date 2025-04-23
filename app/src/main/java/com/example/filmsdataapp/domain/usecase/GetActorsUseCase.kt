package com.example.filmsdataapp.domain.usecase

import com.example.filmsdataapp.domain.model.Actor
import com.example.filmsdataapp.domain.repository.ActorsRepository
import com.example.filmsdataapp.domain.repository.MoviesRepository

class GetActorsUseCase(
    private val repository: ActorsRepository
) {
    suspend operator fun invoke(): List<Actor> {
        return repository.getActors()
    }
}


