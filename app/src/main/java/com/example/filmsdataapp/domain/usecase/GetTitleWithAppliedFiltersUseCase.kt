package com.example.filmsdataapp.domain.usecase

import com.example.filmsdataapp.domain.repository.TitleRepository

class GetTitleWithAppliedFiltersUseCase(
    private val repository: TitleRepository
) {
    suspend operator fun invoke(type:String, genre:String, averageRatingFrom:Int,
                                averageRatingTo:Int, dateOfReleaseFrom:Int,
                                dateOfReleaseTo: Int, language:String): String {
        return repository.getTitleWithAppliedFilters(type, genre, averageRatingFrom, averageRatingTo, dateOfReleaseFrom, dateOfReleaseTo, language)
    }
}

