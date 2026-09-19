package com.example.focus_plan_builder

// Data class representing a completed plan
data class FocusPlan(
    val subject: String,
    val minutes: Int,
    val category: String,
    val breakMinutes: Int
)

// Duration category, using when expression on ranges
fun durationCategory(minutes: Int): String {
    return when {
        minutes < 10 -> "Invalid"
        minutes in 10..29 -> "Quick review"
        minutes in 30..60 -> "Focused session"
        else -> "Extended session" // anything > 60
    }
}

// Recommended break length, using a when expression
fun recommendedBreak(minutes: Int): Int {
    return when {
        minutes in 10..29 -> 5
        minutes in 30..60 -> 10
        else -> 15 // > 60
    }
}