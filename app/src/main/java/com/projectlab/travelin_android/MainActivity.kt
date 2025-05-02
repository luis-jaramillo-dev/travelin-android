package com.projectlab.travelin_android

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.projectlab.core.presentation.designsystem.component.TravelinDatePicker
import com.projectlab.core.presentation.designsystem.theme.TravelinTheme
import java.time.LocalDate


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelinTheme() {
                Scaffold(
                    content = { padding ->
                        val showDialog = remember { mutableStateOf(false) }
                        val selectedDates = remember { mutableStateOf<Pair<Long?, Long?>?>(null) }

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(24.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(onClick = { showDialog.value = true }) {
                                Text("Open Calendar")
                            }

                            selectedDates.value?.let { (start, end) ->
                                Text(
                                    text = "Selected:\nStart = ${start ?: "-"}\nEnd = ${end ?: "-"}",
                                    modifier = Modifier.padding(top = 16.dp)
                                )
                            }

                            if (showDialog.value) {
                                TravelinDatePicker(
                                    onDismiss = { showDialog.value = false },
                                    onConfirm = { dates ->
                                        selectedDates.value = dates
                                        showDialog.value = false
                                    },
                                    availableDateRange = LocalDate.now()..LocalDate.now().plusDays(30)
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}