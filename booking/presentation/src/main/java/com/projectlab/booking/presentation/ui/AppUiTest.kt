package com.projectlab.booking.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.projectlab.booking.presentation.viewmodel.BookingViewModelTest

@Composable
fun BookingApp(
    viewModel: BookingViewModelTest = hiltViewModel()  //Get ViewModel with Hilt
) {
    // A Box to center content
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { viewModel.setupTestData() }) {  // call to function
            Text(text = "Seedar Datos")                    // botton text
        }
    }
}
