package com.projectlab.travelin_android.flight.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.projectlab.booking.presentation.R
import com.projectlab.booking.presentation.flight.FlightPreviewProvider
import com.projectlab.travelin_android.model.Flight
import com.projectlab.core.presentation.designsystem.theme.ColorFamily
import com.projectlab.core.presentation.designsystem.theme.inversePrimaryDark
import com.projectlab.core.presentation.designsystem.theme.scrimDark
import com.projectlab.core.presentation.designsystem.theme.surfaceBrightDark

/*
@Composable
fun FlightListItem(flight: Flight, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)) {
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
}*/
@Composable
fun FlightListItem(
    flight: Flight,
    modifier: Modifier = Modifier
) {

    val airlineCode = flight.segments.firstOrNull()?.airline.orEmpty()
    val airlineDisplay = when (airlineCode) {
        "AA" -> "American Airlines"
        "DL" -> "Delta Air Lines"
        "UA" -> "United Airlines"

        else -> airlineCode.ifEmpty { "Unknown Airline" }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = scrimDark
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icono de la aerolínea
            Icon(
                painter = painterResource(
                    id = when (airlineCode) {
                        "AA" -> R.drawable.ic_american_airlines
                        "DL" -> R.drawable.ic_delta
                        "UA" -> R.drawable.ic_united
                        else -> R.drawable.ic_generic_airplane
                    }
                ),
                contentDescription = airlineDisplay,
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "$airlineDisplay",
                    style = MaterialTheme.typography.bodyLarge
                )
                Row {
                    Column { Text(
                        text = "${flight.origin}",
                        style = MaterialTheme.typography.titleSmall
                    )
                        Text(
                            text = "${flight.departureTime.substringAfter('T')}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Column {
                        Row { Text(
                            text = "${flight.origin} -----------------------> ${flight.destination}",
                            style = MaterialTheme.typography.bodyMedium
                        ) }


                        Text(
                            text = "${flight.durationText}" +
                                    if (flight.stopovers > 0) " • ${flight.stopovers} escala(s)" else "",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Column { Text(
                        text = "${flight.destination}",
                        style = MaterialTheme.typography.titleSmall
                    )
                        Text(
                            text = "${flight.arrivalTime.substringAfter('T')}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                }

                Row { Text(
                    text = "Economic class",
                    style = MaterialTheme.typography.bodySmall
                )
                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "${flight.price.currency} ${flight.price.amount}/per person",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )}


            }


        }
    }
    /*HorizontalDivider(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth(),           // si quieres que respete padding interno
        thickness = 3.dp,                          // grosor de la línea
        color = MaterialTheme.colorScheme.primary // el color que prefieras
    )*/
}
@Preview(showBackground = true)
@Composable
fun FlightListItemPreview(@PreviewParameter(FlightPreviewProvider::class) flight: Flight) {
    FlightListItem(flight = flight)
}

