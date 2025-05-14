package com.projectlab.booking.presentation.flight

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.projectlab.travelin_android.flight.FlightScreen2
import com.projectlab.travelin_android.model.CityLocation
import com.projectlab.travelin_android.model.Flight
import com.projectlab.travelin_android.model.FlightSegment
import com.projectlab.travelin_android.model.Price


@Preview(showBackground = true)
@Composable
fun Preview_FlightScreen2() {
    // Datos de ejemplo
    val sampleSuggestions = listOf(
        CityLocation(city = "Santiago", iataCode = "SCL"),
        CityLocation(city = "Lima",    iataCode = "LIM")
    )
    val sampleFlights = listOf(
        Flight(
            id = "1",
            segments = listOf(
                FlightSegment(
                    airline = "LA",
                    flightNumber = "1234",
                    departureAirport = "SCL",
                    arrivalAirport   = "LAX",
                    departureTime    = "2025-06-01T08:00",
                    arrivalTime      = "2025-06-01T12:00"
                )
            ),
            price = Price(amount = "550.00", currency = "USD")
        )
    )

    FlightScreen2(
        origin                    = "SCL",
        onOriginChange            = {},
        originSuggestions         = sampleSuggestions,
        onOriginSuggestionClick   = {},

        destination               = "LAX",
        onDestinationChange       = {},
        destinationSuggestions    = sampleSuggestions,
        onDestinationSuggestionClick = {},

        departureDate             = "2025-06-01",
        onDepartureDateChange     = {},

        isLoading                 = false,
        errorMessage              = null,

        onSearchFlights           = {},
        flights                   = sampleFlights
    )
}
