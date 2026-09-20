package com.example.focus_plan_builder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.focus_plan_builder.ui.theme.Focus_Plan_BuilderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Focus_Plan_BuilderTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    FocusPlanRoute()
                }
            }
        }
    }
}