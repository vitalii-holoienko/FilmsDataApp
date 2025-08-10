package com.example.filmsdataapp.domain.usecase

import android.net.Uri
import com.example.filmsdataapp.domain.model.Title
import com.example.filmsdataapp.domain.repository.UserRepository
import javax.inject.Inject

class GetUserImageUseCase @Inject constructor(private val repo: UserRepository) {
    operator fun invoke(callback: (Uri) -> Unit){
        repo.getUserImage(callback)
    }
}