package com.projectlab.travelin_android.flight.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.projectlab.travelin_android.model.Flight

@Composable
fun FlightListItem(flight: Flight, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth(), elevation = 2.dp) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            // Icono aerolínea
            Image(
                painter = rememberAsyncImagePainter(flight.airlineLogoUrl),
                contentDescription = flight.airlineName,
                modifier = Modifier.size(40.dp)
            )
            Spacer(Modifier.width(8.dp))
            Column(Modifier.weight(1f)) {
                Text(flight.airlineName, style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(flight.origin, style = MaterialTheme.typography.bodyMedium)
                    Icon(Icons.Default.FlightTakeoff, null, Modifier.padding(horizontal = 4.dp))
                    Text(flight.destination, style = MaterialTheme.typography.bodyMedium)
                }
                Spacer(Modifier.height(2.dp))
                Row {
                    Text(flight.departureTime, style = MaterialTheme.typography.bodySmall)
                    Spacer(Modifier.width(4.dp))
                    Text("→", style = MaterialTheme.typography.bodySmall)
                    Spacer(Modifier.width(4.dp))
                    Text(flight.arrivalTime, style = MaterialTheme.typography.bodySmall)
                }
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(flight.travelClass, style = MaterialTheme.typography.bodySmall)
                Spacer(Modifier.height(4.dp))
                Text("${flight.price} /per person", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}