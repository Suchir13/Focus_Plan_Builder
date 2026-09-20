package com.example.focus_plan_builder

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier

// This composable OWNS the state, validates input, creates the FocusPlan and passes everything down to FocusPlanScreen.
@Composable
fun FocusPlanRoute(modifier: Modifier = Modifier) {
    // RememberSavable so these survive rotation
    var subject by rememberSaveable { mutableStateOf("") }
    var minutesText by rememberSaveable { mutableStateOf("") }

    // Plan is regular remember
    var plan by remember { mutableStateOf<FocusPlan?>(null) }

    // Null-safe conversion, never toInt() directly
    val minutes: Int? = minutesText.toIntOrNull()

    // Derived state, NOT a separate mutable Boolean
    val canCreatePlan = subject.isNotBlank() && minutes != null && minutes in 10..180

    FocusPlanScreen(
        subject = subject,
        minutesText = minutesText,
        plan = plan,
        onSubjectChange = { newValue ->
            subject = newValue
            // Editing input clears the old result
            plan = null
        },
        onMinutesChange = { newValue ->
            minutesText = newValue
            // Editing input clears the old result
            plan = null
        },
        canCreatePlan = canCreatePlan,
        onCreatePlan = {
            if (minutes != null) {
                plan = FocusPlan(
                    subject = subject,
                    minutes = minutes,
                    category = durationCategory(minutes),
                    breakMinutes = recommendedBreak(minutes)
                )
            }
        },
        modifier = modifier
    )
}