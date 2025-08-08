package com.example.filmsdataapp.domain.usecase

import com.example.filmsdataapp.domain.model.Title
import com.example.filmsdataapp.domain.repository.UserRepository
import javax.inject.Inject

class GetUserRatingFotTitleUseCase @Inject constructor(private val repo: UserRepository) {
    operator fun invoke(titleId: String, where: String, onResult: (Int?) -> Unit){
        repo.getUserRatingForTitle(titleId, where, onResult)
    }
}