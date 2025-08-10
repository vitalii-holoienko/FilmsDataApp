package com.example.filmsdataapp.domain.usecase

import com.example.filmsdataapp.domain.model.ActivityData
import com.example.filmsdataapp.domain.repository.UserRepository
import javax.inject.Inject

class GetMonthlyCompletedStatsUseCase @Inject constructor(private val repo: UserRepository) {
    operator fun invoke(uid: String, onResult: (List<ActivityData>) -> Unit){
        repo.getMonthlyCompletedStats(uid, onResult)
    }
}