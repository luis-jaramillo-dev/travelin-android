package flight

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import android.util.Log

@Composable
fun FlightScreen(viewModel: FlightViewModel = hiltViewModel()) {
    val flights = viewModel.flights
    val isLoading = viewModel.isLoading

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = {
            viewModel.loadFlights("SCL", "MIA", "2025-05-15")
        }) {
            Text("Load Flights")
        }

        if (isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(flights) { flight ->
                    Card(modifier = Modifier.padding(8.dp).fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("From: ${flight.origin} To: ${flight.destination}")
                            Text("Departure: ${flight.departureTime}")
                            Text("Price: ${flight.price} USD")
                            Log.i("flight origin",flight.origin)
                            Log.i("flight destination",flight.destination)
                            print(flight)
                        }
                    }
                }
            }
        }
    }
}
