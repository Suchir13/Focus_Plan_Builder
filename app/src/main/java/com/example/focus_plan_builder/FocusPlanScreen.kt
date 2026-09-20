package com.example.focus_plan_builder

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

// This composable only displays the UI. It receives everything it needs as parameters and reports user actions through callbacks
@Composable
fun FocusPlanScreen(
    subject: String,
    minutesText: String,
    plan: FocusPlan?,
    onSubjectChange: (String) -> Unit,
    onMinutesChange: (String) -> Unit,
    canCreatePlan: Boolean,
    onCreatePlan: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        // Screen title
        Text(text = "Focus Plan Builder", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))

        // Brief instructions
        Text(
            text = "Enter a subject and how many minutes you have, then create your plan.",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Subject text field
        OutlinedTextField(
            value = subject,
            onValueChange = onSubjectChange,
            label = { Text("Study subject") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Duration text field
        OutlinedTextField(
            value = minutesText,
            onValueChange = onMinutesChange,
            label = { Text("Available minutes (10-180)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Create Plan button
        Row {
            Button(
                onClick = onCreatePlan,
                enabled = canCreatePlan
            ) {
                Text("Create plan")
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Result card
        if (plan != null) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = plan.subject, style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Duration: ${plan.minutes} minutes")
                    Text(text = "Category: ${plan.category}")
                    Text(text = "Recommended break: ${plan.breakMinutes} minutes")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Study ${plan.subject} for ${plan.minutes} minutes, and then take a ${plan.breakMinutes}-minute break."
                    )
                }
            }
        }
    }
}