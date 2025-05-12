package com.projectlab.travelin_android.flight

import android.app.DatePickerDialog
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.projectlab.travelin_android.model.FlightSearchParams
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.projectlab.travelin_android.model.CityLocation
import com.projectlab.travelin_android.model.Flight
import java.util.Calendar

@Composable
fun FlightScreen2(
    flightViewModel: FlightViewModel,
    onSearchFlights: (FlightSearchParams) -> Unit,
    flights: List<Flight>,
    isLoading: Boolean,
    errorMessage: String?
) {
    var origin by remember { mutableStateOf("") }
    var destination by remember { mutableStateOf("") }
    var departureDate by remember { mutableStateOf("") }
    var flightDate by remember { mutableStateOf("") }

    val originSuggestions = flightViewModel.originSuggestions
    val destinationSuggestions = flightViewModel.destinationSuggestions

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Buscar Vuelos", style = MaterialTheme.typography.titleLarge)
        CityInputField(
            label = "Origen (Ciudad o IATA)",
            value = origin,
            onValueChange = {
                    origin = it
                flightViewModel.searchCityOrigin(it)
            },
            suggestions = originSuggestions,
            onSuggestionClick = { selected ->
                origin = selected
                flightViewModel.clearOriginSuggestions()
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        CityInputField(
            label = "Destino (Ciudad o IATA)",
            value = destination,
            onValueChange = {
                destination = it
                flightViewModel.searchCityDestination(it)
            },
            suggestions = destinationSuggestions,
            onSuggestionClick = { selected ->
                destination = selected // asigna el código IATA seleccionado
                flightViewModel.clearDestinationSuggestions()
            }
        )

        OutlinedTextField(
            value = flightDate,
            onValueChange = { flightDate = it },
            label = { Text("Fecha de salida (YYYY-MM-DD)") },
            singleLine = true
        )
        /*DatePickerField(
            selectedDate = flightDate,
            onDateSelected = {flightDate=it}
        )*/

        Button(
            onClick = {
                if (origin.isNotBlank() && destination.isNotBlank() && flightDate.isNotBlank()) {
                    onSearchFlights(
                        FlightSearchParams(
                            origin = origin,
                            destination = destination,
                            departureDate = flightDate
                        )
                    )
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Buscar vuelos")
        }
        // 🔽 Indicador de carga
        if (isLoading) {
            CircularProgressIndicator()
        }

        // 🔽 Mensaje de error
        errorMessage?.let { msg ->
            Text(
                text = msg,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // 🔽 Lista de vuelos
        FlightResultsScreen(flights)
    }

}
@Composable
fun FlightScreenContainer(
    flightViewModel: FlightViewModel = hiltViewModel()
) {
    val flights by flightViewModel.flights.collectAsState()
    val isLoading by flightViewModel.isLoading.collectAsState()
    val errorMessage by flightViewModel.errorMessage.collectAsState()

    FlightScreen2(
        onSearchFlights = { searchParams ->
            flightViewModel.loadFlights(
                origin = searchParams.origin,
                destination = searchParams.destination,
                date = searchParams.departureDate
            )
        },
        flights = flights,
        isLoading = isLoading,
        errorMessage = errorMessage,
        flightViewModel = flightViewModel
    )
}


@Composable
fun CityInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    suggestions: List<CityLocation>,
    onSuggestionClick: (String) -> Unit
) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        AnimatedVisibility(visible = suggestions.isNotEmpty()) {
            Column {
                suggestions.take(5).forEach { suggestion ->
                    TextButton(
                        onClick = { onSuggestionClick(suggestion.iataCode) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("${suggestion.city} - ${suggestion.iataCode}")
                    }
                }
            }
        }
    }
}
/*@Composable
fun DatePickerField(
    selectedDate: String,
    onDateSelected: (String) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()


    OutlinedTextField(
        value = selectedDate,
        onValueChange = {},
        label = { Text("Fecha del vuelo") },
        readOnly = true,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                Log.d("DatePicker", "Clicked!") // <-- IMPORTANTE
                val datePickerDialog =
                    DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            val formattedDate = "%04d-%02d-%02d".format(year, month + 1, dayOfMonth)
                            onDateSelected(formattedDate)
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).apply{
                        datePicker.minDate = System.currentTimeMillis()
                    }

                datePickerDialog.show() }
    )
}*/
/*@Composable
fun TestDatePickerScreen() {
    var date by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        DatePickerField(
            selectedDate = date,
            onDateSelected = { date = it }
        )
    }
}*/
/*
@Composable
fun DatePickerTestScreen() {
    var selectedDate by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        DatePickerField(
            selectedDate = selectedDate,
            onDateSelected = { selectedDate = it }
        )
    }
}*/
