package com.example.filmsdataapp.domain.usecase

import android.net.Uri
import com.example.filmsdataapp.domain.repository.UserRepository
import javax.inject.Inject

class ChangeUserAccountUseCase @Inject constructor(private val repo: UserRepository) {
    operator fun invoke(nickname: String, description: String, image: Uri?, onSuccess: () -> Unit) =
        repo.changeUserAccount(nickname, description, image, onSuccess)
}