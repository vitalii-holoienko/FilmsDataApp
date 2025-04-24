package com.example.filmsdataapp.domain.usecase

import com.example.filmsdataapp.domain.model.FilterStatus
import com.example.filmsdataapp.domain.repository.TitleRepository

class GetTitleWithAppliedFiltersUseCase(
    private val repository: TitleRepository
) {
    suspend operator fun invoke(filterStatus:FilterStatus): String {
        return repository.getTitleWithAppliedFilters(filterStatus)
    }
}

