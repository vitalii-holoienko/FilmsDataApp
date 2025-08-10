package com.example.filmsdataapp.domain.usecase

import com.example.filmsdataapp.domain.repository.UserRepository
import javax.inject.Inject

class DeleteTitleFromAllListsUseCase @Inject constructor(private val repo: UserRepository) {
    suspend operator fun invoke(id:String){
        repo.deleteTitleFromAllLists(id)
    }
}