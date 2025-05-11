package com.projectlab.booking.presentation.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.projectlab.booking.presentation.viewmodel.BookingViewModelTest

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookingScreen (
    viewModel: BookingViewModelTest = hiltViewModel()
) {

    val seedState by viewModel.seedResult.collectAsState()
    var isLoading by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            isLoading -> {
                CircularProgressIndicator()
            }
            seedState == null -> {
                Button(onClick = {
                    isLoading = true
                    viewModel.setupTestData()
                }) {
                    Text("Generate Test Data")
                }
            }
            seedState!!.isSuccess -> {
                Text("Data generated successfully! ✅")
            }
            else -> {
                Text("Error: ${seedState!!.exceptionOrNull()?.message}")
            }
        }
    }

    // When seedResult change, we stop shows loading
    LaunchedEffect(seedState) {
        if (seedState != null) {
            isLoading = false
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview (showBackground = true)
@Composable
fun BookingScreenPreview() {
    BookingScreen()
}
