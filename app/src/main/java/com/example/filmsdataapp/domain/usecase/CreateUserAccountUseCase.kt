package com.example.filmsdataapp.domain.usecase

import android.net.Uri
import com.example.filmsdataapp.domain.repository.UserRepository
import javax.inject.Inject

class CreateUserAccountUseCase @Inject constructor(private val repo: UserRepository) {
    operator fun invoke(nickname: String, description: String, image: Uri?, onSuccess: () -> Unit) =
        repo.createUserAccount(nickname, description, image, onSuccess)
}