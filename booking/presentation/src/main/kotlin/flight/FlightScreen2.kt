package flight

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import model.FlightSearchParams
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import model.Flight

@Composable
fun FlightScreen2(
    onSearchFlights: (FlightSearchParams) -> Unit,
    flights: List<Flight>,
    isLoading: Boolean,
    errorMessage: String?
) {
    var origin by remember { mutableStateOf("") }
    var destination by remember { mutableStateOf("") }
    var departureDate by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Buscar Vuelos", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(
            value = origin,
            onValueChange = { origin = it },
            label = { Text("Origen (IATA)") },
            singleLine = true
        )

        OutlinedTextField(
            value = destination,
            onValueChange = { destination = it },
            label = { Text("Destino (IATA)") },
            singleLine = true
        )

        OutlinedTextField(
            value = departureDate,
            onValueChange = { departureDate = it },
            label = { Text("Fecha de salida (YYYY-MM-DD)") },
            singleLine = true
        )

        Button(
            onClick = {
                if (origin.isNotBlank() && destination.isNotBlank() && departureDate.isNotBlank()) {
                    onSearchFlights(
                        FlightSearchParams(
                            origin = origin,
                            destination = destination,
                            departureDate = departureDate
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
        errorMessage = errorMessage
    )
}