package com.projectlab.travelin_android.flight.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.projectlab.travelin_android.flight.CityInputField
import com.projectlab.travelin_android.flight.FlightViewModel
import com.projectlab.travelin_android.flight.components.ClassDropdown
import com.projectlab.travelin_android.flight.components.DateRangePickerField
import com.projectlab.travelin_android.flight.components.PassengerInputField

@Composable
fun FlightQueryScreen(
    viewModel: FlightViewModel,
    onNext: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ClassDropdown(
            options = listOf("All", "Economy", "Business"),
            selected = uiState.travelClass,
            onSelect = viewModel::onClassSelected
        )
        CityInputField(
            label = "From",
            value = uiState.origin,
            onValueChange = viewModel::onOriginChange,
            suggestions = viewModel.originSuggestions,
            onSuggestionClick = viewModel::onOriginChosen
        )
        CityInputField(
            label = "To",
            value = uiState.destination,
            onValueChange = viewModel::onDestinationChange,
            suggestions = viewModel.destinationSuggestions,
            onSuggestionClick = viewModel::onDestinationChosen
        )
        DateRangePickerField(
            label = "Fechas",
            displayText = uiState.dateRangeText,
            onClick = { viewModel.openDatePicker(/*…*/ ) }
        )
        PassengerInputField(
            count = uiState.passengerCount,
            onCountChange = viewModel::onPassengerCountChange
        )

        Spacer(Modifier.weight(1f))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("${uiState.estimatedCost} /Person", style = MaterialTheme.typography.titleMedium)
            Button(onClick = {
                viewModel.searchFlights()
                onNext()
            }) {
                Text("Next")
            }
        }
    }
}