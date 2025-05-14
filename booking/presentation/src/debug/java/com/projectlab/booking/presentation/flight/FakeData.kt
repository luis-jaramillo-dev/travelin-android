package com.projectlab.booking.presentation.flight

import com.projectlab.travelin_android.model.Flight
import com.projectlab.travelin_android.model.FlightSegment
import com.projectlab.travelin_android.model.Price

val flights =listOf(
    Flight(
        id = "1",
        segments = listOf(FlightSegment("AA", "100", "SYD", "BKK", "2025-06-01T08:00", "2025-06-01T14:00")),
        price = Price("500.00", "USD")
    ),
    Flight(
        id = "2",
        segments = listOf(
            FlightSegment("QF", "200", "SYD", "SIN", "2025-06-01T09:00", "2025-06-01T13:00"),
            FlightSegment("QF", "201", "SIN", "BKK", "2025-06-01T15:00", "2025-06-01T17:00")
        ),
        price = Price("450.00", "USD")
    ),
    Flight(
        id = "3",
        segments = listOf(FlightSegment("AA", "100", "SYD", "BKK", "2025-06-01T08:00", "2025-06-01T14:00")),
        price = Price("500.00", "USD")
    ),
    Flight(
        id = "4",
        segments = listOf(
            FlightSegment("QF", "200", "SYD", "SIN", "2025-06-01T09:00", "2025-06-01T13:00"),
            FlightSegment("QF", "201", "SIN", "BKK", "2025-06-01T15:00", "2025-06-01T17:00")
        ),
        price = Price("450.00", "USD")
    ),
    Flight(
        id = "5",
        segments = listOf(
            FlightSegment("QF", "200", "SYD", "SIN", "2025-06-01T09:00", "2025-06-01T13:00"),
            FlightSegment("QF", "201", "SIN", "BKK", "2025-06-01T15:00", "2025-06-01T17:00")
        ),
        price = Price("450.00", "USD")
    )
)