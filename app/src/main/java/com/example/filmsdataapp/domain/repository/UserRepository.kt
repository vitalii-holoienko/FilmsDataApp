package com.example.filmsdataapp.domain.repository

import android.net.Uri
import com.example.filmsdataapp.domain.model.Title

interface UserRepository {
    fun createUserAccount(nickname: String, description: String, image: Uri?, onSuccess: () -> Unit)
    fun setUserRatingForTitle(title: Title, rating: Float, where: String)
    fun getUserRatingForTitle(titleId: String, where: String, onResult: (Int?) -> Unit)
    fun changeUserAccount(nickname: String, description: String, image: Uri?, onSuccess: () -> Unit)

}