package com.example.filmsdataapp.domain.usecase

import com.example.filmsdataapp.domain.repository.UserRepository
import javax.inject.Inject

class GetUserNickNameUseCase @Inject constructor(private val repo: UserRepository) {
    operator fun invoke(callback: (String) -> Unit){
        repo.getUserNickname(callback)
    }
}