package com.example.filmsdataapp.domain.usecase

import com.example.filmsdataapp.domain.model.Title
import com.example.filmsdataapp.domain.repository.UserRepository
import javax.inject.Inject

class AddTitleToWatchingListUseCase @Inject constructor(private val repo: UserRepository) {
    operator fun invoke(title : Title) {
        repo.addTitleToWatchingList(title)
    }
}