package flight

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import model.Flight
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightResultsScreen(flights: List<Flight>) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Resultados de Vuelos") })
        }
    ) { innerPadding ->
        FlightList(
            flights = flights,
            modifier = Modifier.padding(innerPadding)
        )
    }
}