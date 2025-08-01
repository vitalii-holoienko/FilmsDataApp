package com.example.filmsdataapp.domain.usecase

import com.example.filmsdataapp.domain.model.Actor
import com.example.filmsdataapp.domain.repository.ActorsRepository
import com.example.filmsdataapp.domain.repository.MoviesRepository
import javax.inject.Inject

class GetActorsUseCase @Inject constructor(
    private val repository: ActorsRepository
) {
    suspend operator fun invoke(): List<Actor> {
        return repository.getActors()
    }
}


