package com.projectlab.booking.presentation.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.projectlab.booking.presentation.ui.BookingViewModelTest
import com.projectlab.core.presentation.designsystem.component.TravelinTextField
import com.projectlab.core.presentation.designsystem.theme.Spacing.Spacer

/**
 * BookingScreen is a Composable function that displays the booking screen.
 * It shows a button to generate test data and a loading indicator while the data is being generated.
 * It's used for testing purposes in the debug build variant.
 *
 * @param viewModel The BookingViewModelTest instance used to manage the state of the screen.
 * @author ricardoceadev
 */

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

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        // Search all itineraries by user ID
        var userIdInput by remember { mutableStateOf("") }
        TextField(
            value = userIdInput,
            onValueChange = { userIdInput = it },
            label = { Text("User ID") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Button(onClick = { viewModel.fetchAllItineraries(userIdInput)}) {
            Text("Search Itineraries")
        }

        // Shows the list of itineraries for the user
        val itineraries by viewModel.itineraries.collectAsState()
        if (itineraries.isNotEmpty()) {
            itineraries.forEach { itin ->
                Text("- ${itin.title} [${itin.id}]")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Search itinerary by ID
        var itinIdInput by remember { mutableStateOf("") }
        TextField(
            value = itinIdInput,
            onValueChange = { itinIdInput = it },
            label = { Text("Itinerary ID") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Button(onClick = { viewModel.fetchItineraryById(userIdInput, itinIdInput) }) {
            Text("Search Itinerary")
        }

        // Shows the itinerary details if found
        val single by viewModel.singleItinerary.collectAsState()
        single?.let {itin ->
            Text("Found: ${itin.title} (${itin.startDate} → ${itin.endDate})")
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
