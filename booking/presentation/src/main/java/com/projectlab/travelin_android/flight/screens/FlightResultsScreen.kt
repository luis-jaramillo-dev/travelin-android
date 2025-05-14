package com.projectlab.travelin_android.flight.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.projectlab.travelin_android.flight.FlightViewModel
import com.projectlab.travelin_android.flight.components.FlightListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightResultsScreen(
    viewModel: FlightViewModel,
    onBack: () -> Unit
) {
    val s by viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Choose your trip") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null) } }
            )
        }
    ) { pad ->
        Column(Modifier.padding(pad).verticalScroll(rememberScrollState()).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Trips for \"${s.destination}\"", style = MaterialTheme.typography.bodyMedium)
            s.flights.forEach { FlightListItem(it) }
            if (s.flights.size > 3) TextButton(onClick = viewModel::loadMore) { Text("Show +${s.flights.size - 3} more available") }
        }
    }
}