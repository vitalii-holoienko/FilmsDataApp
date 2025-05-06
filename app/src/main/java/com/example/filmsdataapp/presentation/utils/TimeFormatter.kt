package com.example.filmsdataapp.presentation.utils

object TimeFormatter {
    fun formatDuration(minutes: Int?): String {
        if(minutes == null) return "-"
        val hours = minutes / 60
        val mins = minutes % 60

        val hoursPart = if (hours > 0) "$hours hour${if (hours != 1) "s" else ""}" else ""
        val minutesPart = if (mins > 0) "$mins minute${if (mins != 1) "s" else ""}" else ""

        return listOf(hoursPart, minutesPart)
            .filter { it.isNotEmpty() }
            .joinToString(", ")
    }
}