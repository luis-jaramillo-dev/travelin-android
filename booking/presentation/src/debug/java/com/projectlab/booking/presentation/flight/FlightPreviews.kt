package com.projectlab.booking.presentation.flight

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.projectlab.travelin_android.flight.CityInputField
import com.projectlab.travelin_android.flight.FlightViewModel
//import com.projectlab.travelin_android.flight.UiTravelClass
import com.projectlab.travelin_android.flight.components.ClassDropdown
import com.projectlab.travelin_android.flight.components.FlightListItem
//import com.projectlab.travelin_android.flight.screens.DatePickerField
//import com.projectlab.travelin_android.flight.screens.PassengerInputGroup
import com.projectlab.travelin_android.model.CityLocation
import com.projectlab.travelin_android.model.Flight
import com.projectlab.travelin_android.model.FlightSegment
import com.projectlab.travelin_android.model.Price
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

// --- Composables & Previews ---
@Composable
fun FlightQueryScreen(
    viewModel: IFlightViewModel,
    onNext: () -> Unit
) { val s by viewModel.uiState.collectAsState()
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        ClassDropdown(
            options = FlightViewModel.UiTravelClass.entries,
            selected = s.travelClass.toString(),
            onSelect = viewModel::onClassSelected
        )
        CityInputField("From", s.origin, viewModel::onOriginChange, s.originSuggestions, viewModel::onOriginChosen)
        CityInputField("To", s.destination, viewModel::onDestinationChange, s.destinationSuggestions, viewModel::onDestinationChosen)
        //DatePickerField("Departure", s.departureDate, viewModel::onDepartureDateSelected)
        //DatePickerField("Return (optional)", s.returnDate, viewModel::onReturnDateSelected)
        //PassengerInputGroup(s.adults, s.children, s.infants, viewModel::onPassengerCounts)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = s.nonStop, onCheckedChange = { viewModel.onNonStopToggled() })
            Text("Non-stop only")
        }
        OutlinedTextField(
            value = s.maxPrice,
            onValueChange = viewModel::onMaxPriceChange,
            label = { Text("Max price (optional)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(Modifier.weight(1f))
        Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            Text(s.flights.firstOrNull()?.price?.let { "Est. ${it.amount} ${it.currency}" } ?: "— / person", style = MaterialTheme.typography.titleMedium)
            Button(onClick = { viewModel.searchFlights(onNext) }) { Text("Search") }
        }
    } }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightResultsScreen(
    viewModel: IFlightViewModel,
    onBack: () -> Unit
) { val s by viewModel.uiState.collectAsState()
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
    } }

@Preview(showBackground = true)
@Composable
fun Preview_FlightQueryScreen() {
    FlightQueryScreen(viewModel = PreviewFlightViewModel(), onNext = {})
}

/*@Preview(showBackground = true)
@Composable
fun Preview_FlightResultsScreen() {
    FlightResultsScreen(viewModel = PreviewFlightViewModel(), onBack = {})
}*/
/*
@Preview(showBackground = true)
@Composable
fun Preview_FlightResultsScreen() {
    val previewViewModel = object : IFlightViewModel {
        private val _uiState = MutableStateFlow(
            FlightViewModel.UiState(
                destination = "Bangkok",
                flights = flights
            )
        )

        override val uiState = _uiState.asStateFlow()
        override fun onClassSelected(c: FlightViewModel.UiTravelClass) {}
        override fun onOriginChange(i: String) {}
        override fun onOriginChosen(l: CityLocation) {}
        override fun onDestinationChange(i: String) {}
        override fun onDestinationChosen(l: CityLocation) {}
        override fun onDepartureDateSelected(d: String) {}
        override fun onReturnDateSelected(date: String?) {}
        override fun onPassengerCounts(a: Int, c: Int, i: Int) {}
        override fun onNonStopToggled() {}
        override fun onMaxPriceChange(p: String) {}
        override fun searchFlights(onComplete: () -> Unit) {}
        override fun loadMore() {}
    }

    FlightResultsScreen(viewModel = previewViewModel, onBack = {})
}
*/
