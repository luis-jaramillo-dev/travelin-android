package com.projectlab.travelin_android.flight.components.moleculas

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.unit.dp
import com.projectlab.travelin_android.flight.components.atomos.LabelledDropdown
import com.projectlab.travelin_android.flight.components.atomos.LabelledTextField
import com.projectlab.travelin_android.flight.components.atomos.DateRangePickerField
import com.projectlab.travelin_android.flight.components.atomos.PassengerPickerBottomSheet
import com.projectlab.travelin_android.flight.components.atomos.ToggleSwitch
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import com.projectlab.travelin_android.flight.components.atomos.CityInputField
import com.projectlab.travelin_android.model.CityLocation
import com.projectlab.core.presentation.designsystem.theme.CustomShapes
import com.projectlab.core.presentation.designsystem.theme.TravelinTheme


/*
@Composable
fun FlightQueryForm(
    travelClass: String, onClassSelect: (String)->Unit,
    origin: String, onOriginChange: (String)->Unit,
    destination: String, onDestinationChange: (String)->Unit,
    dates: Pair<String, String>?, onDatesSelect: (Pair<String,String>)->Unit,
    passengers: PassengerCounts, onPassengerChange: (PassengerCounts)->Unit,
    nonStopOnly: Boolean, onNonStopToggle: (Boolean)->Unit,
    maxPrice: String, onMaxPriceChange: (String)->Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        LabelledDropdown("Class", travelClass, "All", onClassSelect)
        LabelledTextField("From", origin, "Select your origin", onOriginChange)
        LabelledTextField("To", destination, "Select your destination", onDestinationChange)
        DateRangePickerField("Dates", dates, onDatesSelect)
        PassengerPickerBottomSheet(passengers, onPassengerChange)
        ToggleSwitch("Non‑stop only", nonStopOnly, onNonStopToggle)
        LabelledTextField("Max price (optional)", maxPrice, "Enter the max price", onMaxPriceChange)
    }
}*/
@Composable
fun FlightQueryForm(
    travelClass: String,
    optionsClass: List<String>,
    onClassSelect: (String) -> Unit,

    origin: String,
    originSuggestions: List<CityLocation>,
    onOriginChange: (String) -> Unit,
    onOriginSelect: (CityLocation) -> Unit,

    destination: String,
    destinationSuggestions: List<CityLocation>,
    onDestinationChange: (String) -> Unit,
    onDestinationSelect: (CityLocation) -> Unit,


    dateRange: Pair<String, String>?,
    onDateRangeSelect: (Pair<String, String>) -> Unit,

    passengerCount: Int,
    onPassengerCountChange: (Int) -> Unit,

    nonStopOnly: Boolean,
    onNonStopToggle: (Boolean) -> Unit,

    maxPrice: String,
    onMaxPriceChange: (String) -> Unit,

    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // 1. Clase de viaje
        LabelledDropdown(
            label       = "Class",
            selected    = travelClass,
            placeholder = "All",
            options     = optionsClass,
            onSelect    = onClassSelect,
            modifier    = Modifier.fillMaxWidth()
        )

        // Campo Origen
        CityInputField(
            label = "From",
            value = origin,
            placeholder           = "Select your origin",
            onValueChange = onOriginChange,
            suggestions = originSuggestions,
            onSuggestionClick = onOriginSelect,
            modifier = Modifier.fillMaxWidth()
        )

        // Campo Destino
        CityInputField(
            label = "To",
            value = destination,
            placeholder           = "Select your destination",
            onValueChange = onDestinationChange,
            suggestions = destinationSuggestions,
            onSuggestionClick = onDestinationSelect,
            modifier = Modifier.fillMaxWidth()
        )



        // 3. Rango de fechas
        DateRangePickerField(
            label               = "Dates",
            dateRange           = dateRange,
            placeholder         = "dd mm. - dd mm.",
            onDateRangeSelected = onDateRangeSelect,
            modifier            = Modifier.fillMaxWidth()
        )

        // 4. Selector de pasajeros
        PassengerPickerField(
            passengerCount          = passengerCount,
            onPassengerCountChange  = onPassengerCountChange,
            modifier                = Modifier.fillMaxWidth()
        )

        // 5. Solo sin escalas
        ToggleSwitch(
            label    = "Non‑stop only",
            checked  = nonStopOnly,
            onToggle = onNonStopToggle,
            modifier = Modifier.fillMaxWidth()
        )

        // 6. Precio máximo opcional
        LabelledTextField(
            label          = "Max price (optional)",
            value          = maxPrice,
            placeholder    = "Enter the max price",
            onValueChange  = onMaxPriceChange,
            modifier       = Modifier.fillMaxWidth(),
        )
    }
}