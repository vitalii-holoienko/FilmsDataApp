package com.example.filmsdataapp.domain.usecase

import com.example.filmsdataapp.domain.repository.UserRepository
import javax.inject.Inject


class FetchUserHistoryUseCase @Inject constructor(private val repo: UserRepository) {
    operator fun invoke(onResult: (List<String>) -> Unit, onError: (Exception) -> Unit){
        repo.fetchUserHistory(onResult, onError)
    }
}