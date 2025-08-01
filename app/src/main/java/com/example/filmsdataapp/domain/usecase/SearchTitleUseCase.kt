package com.example.filmsdataapp.domain.usecase

import com.example.filmsdataapp.domain.model.FilterStatus
import com.example.filmsdataapp.domain.model.Title
import com.example.filmsdataapp.domain.repository.TitleRepository
import javax.inject.Inject

class SearchTitleUseCase @Inject constructor(
    private val repository: TitleRepository
) {
    suspend operator fun invoke(query:String): List<Title> {
        return repository.searchTitle(query)
    }
}

