package com.example.filmsdataapp.domain.usecase

import com.example.filmsdataapp.domain.model.Title
import com.example.filmsdataapp.domain.repository.UserRepository
import javax.inject.Inject

class SetUserRatingFotTitleUseCase @Inject constructor(private val repo: UserRepository) {
    operator fun invoke(title: Title, rating: Float, where: String){
        repo.setUserRatingForTitle(title, rating, where)
    }
}