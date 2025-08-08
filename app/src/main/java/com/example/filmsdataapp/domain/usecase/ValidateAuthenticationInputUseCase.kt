package com.example.filmsdataapp.domain.usecase

import javax.inject.Inject

class ValidateAuthenticationInputUseCase @Inject constructor(){
    operator fun invoke(email: String, password: String): Pair<Boolean, Pair<String, String>> {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}\$")
        val forbiddenCharsRegex = Regex("[\\s\"',;]")

        if (email.isBlank()) return false to ("email" to "Email cannot be empty.")
        if (!emailRegex.matches(email)) return false to ("email" to "Please enter a valid email address.")
        if (forbiddenCharsRegex.containsMatchIn(email)) return false to ("email" to "Email contains invalid characters.")

        if (password.isBlank()) return false to ("password" to "Password cannot be empty.")
        if (password.length < 8) return false to ("password" to "Password must be at least 8 characters long.")
        if (forbiddenCharsRegex.containsMatchIn(password)) return false to ("password" to "Password contains invalid characters.")

        val containsLetter = password.any { it.isLetter() }
        val containsDigit = password.any { it.isDigit() }

        if (!containsLetter || !containsDigit) {
            return false to ("password" to "Password must contain at least one letter and one number.")
        }

        return true to ("" to "")
    }
}