package flight

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.Flight

@Composable
fun FlightCard(flight: Flight, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "${flight.origin} → ${flight.destination}",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Duración: ${flight.durationText} | Escalas: ${flight.stopovers}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(8.dp))

            flight.segments.forEachIndexed { index, segment ->
                Text(
                    text = "Segmento ${index + 1}: ${segment.departureAirport} → ${segment.arrivalAirport}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "${segment.airline} ${segment.flightNumber} | ${segment.departureTime} - ${segment.arrivalTime}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(6.dp))
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            Text(
                text = "Precio: USD ${flight.price}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF2E7D32)
            )
        }
    }
}

@Composable
fun FlightList(flights: List<Flight>,modifier: Modifier=Modifier) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(flights) { flight ->
            FlightCard(flight = flight)
        }
    }
}