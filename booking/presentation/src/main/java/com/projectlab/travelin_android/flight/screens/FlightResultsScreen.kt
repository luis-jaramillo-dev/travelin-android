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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.projectlab.booking.presentation.flight.FakeFlightViewModel
import com.projectlab.booking.presentation.flight.IFlightViewModel
import com.projectlab.core.presentation.designsystem.theme.TravelinTheme
import com.projectlab.travelin_android.flight.FlightViewModel
import com.projectlab.travelin_android.flight.components.FlightListItem
import com.projectlab.travelin_android.flight.components.atomos.flightqueryscreen.BackButton
import com.projectlab.travelin_android.flight.components.atomos.flightqueryscreen.ScreenTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightResultsScreen(
    viewModel: IFlightViewModel,
    onBack: () -> Unit,
    onShow: () -> Unit
) {
    val s by viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            BackButton(onBack = onBack, modifier = Modifier.padding(start = 8.dp))
        }
    ) { pad ->
        Column(
            modifier = Modifier
                .padding(pad)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ScreenTitle(
                title = "Choose your trip",
                subtitle = "Trips available for ${s.destination}"
            )
            s.flights.forEach { FlightListItem(it) }
            if (s.flights.size > 3) TextButton(onClick = viewModel::loadMore) { Text("Show +${s.flights.size - 3} more available") }
        }
    }
}
@Preview(showBackground = true, name = "FlightResultsScreen Preview")
@Composable
fun FlightResultsScreenPreview() {
    TravelinTheme {
        FlightResultsScreen (
            viewModel = FakeFlightViewModel(),
            onBack = {},
            onShow = {}
        )
    }
}