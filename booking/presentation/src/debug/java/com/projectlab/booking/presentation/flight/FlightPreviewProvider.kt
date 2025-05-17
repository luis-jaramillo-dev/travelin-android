package com.projectlab.booking.presentation.flight

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.projectlab.travelin_android.model.Flight
import com.projectlab.travelin_android.model.FlightSegment
import com.projectlab.travelin_android.model.Price

class FlightPreviewProvider : PreviewParameterProvider<Flight> {
    override val values: Sequence<Flight> = sequenceOf(
        Flight(
            id = "1",
            segments = listOf(
                FlightSegment(airline = "AA",
                flightNumber = "001",
                departureAirport = "JFK",
                arrivalAirport = "LAX",
                departureTime = "2025-05-13T08:00",
                arrivalTime = "2025-05-13T11:00")
            ),

            price = Price(amount = "320.0", currency = "USD")


        ),
        Flight(
            id = "2",
            segments = listOf(
                FlightSegment(airline = "DL",
                    flightNumber = "456",
                    departureAirport = "ATL",
                    arrivalAirport = "ORD",
                    departureTime = "2025-05-13T14:30",
                    arrivalTime = "2025-05-13T16:45")),

            price = Price(amount = "210.5", currency = "USD")

        ),
        Flight(
            id = "3",
            segments = listOf(
                FlightSegment(
                    airline = "UA",
                    flightNumber = "789",
                    departureAirport = "SFO",
                    arrivalAirport = "SEA",
                    departureTime = "2025-05-13T10:00",
                    arrivalTime = "2025-05-13T11:40"
            )
            )
            ,

            price = Price(amount = "150.0", currency = "USD"),

            )
        )
}