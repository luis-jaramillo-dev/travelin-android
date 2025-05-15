package com.projectlab.travelin_android.flight.components.moleculas

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.unit.dp
import com.projectlab.travelin_android.flight.components.atomos.LabelledDropdown
import com.projectlab.travelin_android.flight.components.atomos.LabelledTextField
import com.projectlab.travelin_android.flight.components.atomos.DateRangePickerField
import com.projectlab.travelin_android.flight.components.atomos.PassengerPickerBottomSheet
import com.projectlab.travelin_android.flight.components.atomos.ToggleSwitch
import androidx.compose.foundation.layout.Arrangement


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
}