package com.example.filmsdataapp.domain.usecase

import com.example.filmsdataapp.domain.model.Title
import com.example.filmsdataapp.domain.repository.UserRepository
import javax.inject.Inject

class GetWatchingTitlesUseCase @Inject constructor(private val repo: UserRepository) {
    operator fun invoke(callback: (List<Title>) -> Unit) {
        repo.getWatchingTitles(callback)
    }
}