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

    fun formatHoursToReadableTime(hours: Int): String {
        val months = hours / (24 * 30)
        val weeks = (hours % (24 * 30)) / (24 * 7)
        val days = (hours % (24 * 7)) / 24
        val remainingHours = hours % 24

        val parts = mutableListOf<String>()
        if (months > 0) parts.add("$months month${if (months > 1) "s" else ""}")
        if (weeks > 0) parts.add("$weeks week${if (weeks > 1) "s" else ""}")
        if (days > 0) parts.add("$days day${if (days > 1) "s" else ""}")
        if (remainingHours > 0) parts.add("$remainingHours hour${if (remainingHours > 1) "s" else ""}")

        return if (parts.isEmpty()) "0 hours" else parts.take(2).joinToString(" & ")
    }
}