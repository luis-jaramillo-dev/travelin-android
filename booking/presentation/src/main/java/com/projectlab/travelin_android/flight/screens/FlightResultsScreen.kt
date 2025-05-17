package com.projectlab.travelin_android.flight.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.projectlab.booking.presentation.flight.FakeFlightViewModel
import com.projectlab.booking.presentation.flight.IFlightViewModel
import com.projectlab.core.presentation.designsystem.theme.TravelinTheme
import com.projectlab.core.presentation.designsystem.theme.onSurfaceVariantDark
import com.projectlab.travelin_android.flight.FlightViewModel
import com.projectlab.travelin_android.flight.components.FlightListItem
import com.projectlab.travelin_android.flight.components.atomos.flightqueryscreen.BackButton
import com.projectlab.travelin_android.flight.components.atomos.flightqueryscreen.ScreenTitle
import com.projectlab.core.presentation.designsystem.theme.outlineDark
import com.projectlab.core.presentation.designsystem.theme.scrimDark

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
        LazyColumn(
            // solo padding vertical para status/nav bars
            contentPadding = PaddingValues(
                top = pad.calculateTopPadding() + 16.dp,
                bottom = pad.calculateBottomPadding() + 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(scrimDark)

        ) {
            item {
                ScreenTitle(
                    title = "Choose your trip",
                    subtitle = "Trips available for ${s.destination}",
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }

            itemsIndexed(s.flights) { index, flight ->
                // Cada ítem envuelto para aplicar el padding horizontal
                Column {
                    FlightListItem(
                        flight = flight,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                    )

                    // Divider a pantalla completa, sin padding horizontal
                    if (index < s.flights.lastIndex) {
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp),
                            color = onSurfaceVariantDark
                        )
                    }
                }
            }

            if (s.flights.size > 3) {
                item {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        TextButton(onClick = viewModel::loadMore) {
                            Text("Show +${s.flights.size - 3} more available")
                        }
                    }
                }
            }
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