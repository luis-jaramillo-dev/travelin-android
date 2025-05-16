package com.projectlab.travelin_android.flight.screens

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.projectlab.booking.presentation.flight.FakeFlightViewModel
import com.projectlab.booking.presentation.flight.IFlightViewModel
import com.projectlab.travelin_android.flight.CityInputField
import com.projectlab.travelin_android.flight.FlightViewModel
//import com.projectlab.travelin_android.flight.UiTravelClass
import com.projectlab.travelin_android.flight.components.ClassDropdown
import com.projectlab.travelin_android.flight.components.atomos.BackButton
import com.projectlab.travelin_android.flight.components.atomos.ScreenTitle
import com.projectlab.travelin_android.flight.components.moleculas.FlightQueryBottomBar
import com.projectlab.travelin_android.flight.components.moleculas.FlightQueryForm
import java.util.Calendar

/*
@Composable
fun FlightQueryScreen(
    viewModel: FlightViewModel,
    onNext: () -> Unit
) {
    val s by viewModel.uiState.collectAsState()
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        ClassDropdown(
            options = FlightViewModel.UiTravelClass.entries,
            selected = s.travelClass.toString(),
            onSelect = viewModel::onClassSelected
        )
        CityInputField("From", s.origin, viewModel::onOriginChange, s.originSuggestions, viewModel::onOriginChosen)
        CityInputField("To", s.destination, viewModel::onDestinationChange, s.destinationSuggestions, viewModel::onDestinationChosen)
        DatePickerField("Departure", s.departureDate, viewModel::onDepartureDateSelected)
        DatePickerField("Return (optional)", s.returnDate, viewModel::onReturnDateSelected)
        PassengerInputGroup(s.adults, s.children, s.infants, viewModel::onPassengerCounts)
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
    }
}
@Composable
fun DatePickerField(
    label: String,
    date: String,
    onDateSelected: (String) -> Unit
) {
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = remember {
        DatePickerDialog(context, { _, y, m, d ->
            val selected = String.format("%04d-%02d-%02d", y, m + 1, d)
            onDateSelected(selected)
        }, year, month, day)
    }

    OutlinedTextField(
        value = date,
        onValueChange = {},
        readOnly = true,
        label = { Text(label) },
        modifier = Modifier.clickable { datePickerDialog.show() }
    )
}

@Composable
fun PassengerInputGroup(
    adults: Int,
    children: Int,
    infants: Int,
    onCountChange: (Int, Int, Int) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        PassengerRow("Adults", adults) { onCountChange(it, children, infants) }
        PassengerRow("Children", children) { onCountChange(adults, it, infants) }
        PassengerRow("Infants", infants) { onCountChange(adults, children, it) }
    }
}

@Composable
fun PassengerRow(label: String, count: Int, onCountChange: (Int) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(label, Modifier.weight(1f))
        IconButton(onClick = { if (count > 0) onCountChange(count - 1) }) { Icon(Icons.Filled.Remove, contentDescription = "-") }
        Text("$count", Modifier.padding(horizontal = 8.dp))
        IconButton(onClick = { onCountChange(count + 1) }) { Icon(Icons.Filled.Add, contentDescription = "+") }
    }
}*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightQueryScreen(
    viewModel: IFlightViewModel,
    onBack: ()->Unit,
    onNext: ()->Unit
) {
    // 1. Obtener el estado del ViewModel
    val uiState by viewModel.uiState.collectAsState()

    // 2. Scaffold con TopBar y BottomBar
    Scaffold(
        topBar = {
            BackButton(onBack = onBack, modifier = Modifier.padding(start = 8.dp))
        },
        bottomBar = {
            FlightQueryBottomBar(
                totalAmount = uiState.estimatedPrice?.amount?.toString() ?: "—",
                unit = "/person",
                onNext = { viewModel.searchFlights(onNext) }
            )
        }
    ) { paddingValues ->
        // 3. Contenido principal con scroll
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ScreenTitle(
                title = "Detail Booking",
                subtitle = "Get the best out of derleng by creating an account"
            )

            FlightQueryForm(
                travelClass             = uiState.travelClass.display,
                optionsClass            = FlightViewModel.UiTravelClass.entries.map { it.display },
                onClassSelect           = { /* ... */ },

                origin                  = uiState.origin,
                originSuggestions       = uiState.originSuggestions,
                onOriginChange          = viewModel::onOriginChange,
                onOriginSelect          = viewModel::onOriginChosen,

                destination             = uiState.destination,
                destinationSuggestions  = uiState.destinationSuggestions,
                onDestinationChange     = viewModel::onDestinationChange,
                onDestinationSelect     = viewModel::onDestinationChosen,

                dateRange               = uiState.dateRange,
                onDateRangeSelect       = viewModel::onDateRangeSelected,

                passengerCount          = uiState.totalPassengers,
                onPassengerCountChange  = { viewModel.onPassengerCounts(it, 0, 0) },

                nonStopOnly             = uiState.nonStop,
                onNonStopToggle         = { _: Boolean ->
                    viewModel.onNonStopToggled()
                },

                maxPrice                = uiState.maxPrice,
                onMaxPriceChange        = viewModel::onMaxPriceChange
            )
        }
    }
}
@Preview(showBackground = true, name = "FlightQueryScreen Preview")
@Composable
fun FlightQueryScreenPreview() {
     // si tienes un tema propio
        FlightQueryScreen(
            viewModel = FakeFlightViewModel(),
            onBack = {},
            onNext = {}
        )
    }


