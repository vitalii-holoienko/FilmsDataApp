package com.example.filmsdataapp.domain.usecase

import com.example.filmsdataapp.domain.model.FilterStatus
import com.example.filmsdataapp.domain.model.Title
import com.example.filmsdataapp.domain.repository.TitleRepository

class GetTitlesReleasedInCertainYear(
    private val repository: TitleRepository
) {
    suspend operator fun invoke(year:Int): List<Title> {
        return repository.getTitlesReleasedInCertainYear(year)
    }
}

