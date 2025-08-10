package com.example.filmsdataapp.domain.usecase

import com.example.filmsdataapp.domain.model.ActivityData
import com.example.filmsdataapp.domain.repository.UserRepository
import javax.inject.Inject

class CheckIfUserHasTitleInListsUseCase @Inject constructor(private val repo: UserRepository) {
    operator fun invoke(titleId: String,
                        callback: (String) -> Unit){
        repo.checkIfUserHasTitleInLists(titleId, callback)
    }
}