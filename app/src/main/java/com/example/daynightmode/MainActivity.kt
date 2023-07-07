package com.example.daynightmode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.daynightmode.ui.AppSwitch
import com.example.daynightmode.ui.SunAndMoonCanvasContent
import com.example.daynightmode.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                var isDayMode by remember { mutableStateOf(true) }
                AppTheme {
                    DayNightScreen(isDayMode = isDayMode) {
                        isDayMode = it
                    }
                }
            }
        }
    }
}

@Composable
fun DayNightScreen(
    isDayMode: Boolean,
    onDayModeChanged: (Boolean) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        SunAndMoonCanvasContent(isDayMode = isDayMode)
        //SunAndMoonBoxesContent(isDayMode = isDayMode)

        Text(
            modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
            text = "Сменить тему",
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.h6
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "🌘")
            AppSwitch(
                modifier = Modifier.padding(horizontal = 16.dp),
                checked = isDayMode,
                onCheckedChange = onDayModeChanged
            )
            Text(text = "☀️")
        }
    }
}