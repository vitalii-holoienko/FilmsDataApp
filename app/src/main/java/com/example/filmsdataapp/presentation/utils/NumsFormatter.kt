package com.example.filmsdataapp.presentation.utils

object NumsFormatter {
    fun formatMillions(number: Long?): String {
        if(number == null) return "-"
        val millions = number / 1_000_000
        return "$millions million${if (millions != 1L) "s" else ""}"
    }
}