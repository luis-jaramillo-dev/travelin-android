package com.projectlab.booking.presentation.flight

import com.projectlab.travelin_android.model.CityLocation
import com.projectlab.travelin_android.model.Flight
import com.projectlab.travelin_android.model.FlightQueryParams
import com.projectlab.travelin_android.model.FlightSegment
import com.projectlab.travelin_android.model.Price
import com.projectlab.travelin_android.repository.FlightRepository

class FakeFlightRepository: FlightRepository {
    override suspend fun searchCityLocations(keyword: String) = listOf(CityLocation("TestCity", "TST"))
    override suspend fun getFlights(params: FlightQueryParams) = sampleFlights
}

// --- Sample Data ---
val sampleFlights = listOf(
    Flight(
        id = "1",
        segments = listOf(FlightSegment("AA", "100", "SYD", "BKK", "2025-06-01T08:00", "2025-06-01T14:00")),
        price = Price("500.00", "USD")
    )
)

val sample10Flights = listOf(
    Flight(
        id = "1",
        segments = listOf(
            FlightSegment("AA", "100", "SCL", "MIA", "2025-06-01T08:00", "2025-06-01T15:00")
        ),
        price = Price("620.00", "USD")
    ),
    Flight(
        id = "2",
        segments = listOf(
            FlightSegment("LATAM", "245", "SCL", "LIM", "2025-06-02T07:00", "2025-06-02T09:30"),
            FlightSegment("LATAM", "990", "LIM", "MEX", "2025-06-02T11:00", "2025-06-02T15:30")
        ),
        price = Price("780.00", "USD")
    ),
    Flight(
        id = "3",
        segments = listOf(
            FlightSegment("DL", "890", "JFK", "LAX", "2025-06-03T06:00", "2025-06-03T09:00")
        ),
        price = Price("399.00", "USD")
    ),
    Flight(
        id = "4",
        segments = listOf(
            FlightSegment("AF", "160", "CDG", "FCO", "2025-06-04T10:00", "2025-06-04T12:00"),
            FlightSegment("AF", "290", "FCO", "ATH", "2025-06-04T13:30", "2025-06-04T16:00")
        ),
        price = Price("500.00", "EUR")
    ),
    Flight(
        id = "5",
        segments = listOf(
            FlightSegment("LH", "300", "FRA", "JFK", "2025-06-05T14:00", "2025-06-05T18:00")
        ),
        price = Price("560.00", "USD")
    ),
    Flight(
        id = "6",
        segments = listOf(
            FlightSegment("IB", "123", "MAD", "BCN", "2025-06-06T08:00", "2025-06-06T09:30"),
            FlightSegment("IB", "456", "BCN", "LIS", "2025-06-06T10:30", "2025-06-06T12:00")
        ),
        price = Price("320.00", "EUR")
    ),
    Flight(
        id = "7",
        segments = listOf(
            FlightSegment("KLM", "777", "AMS", "LHR", "2025-06-07T06:45", "2025-06-07T07:45")
        ),
        price = Price("150.00", "GBP")
    ),
    Flight(
        id = "8",
        segments = listOf(
            FlightSegment("QF", "10", "SYD", "SIN", "2025-06-08T12:00", "2025-06-08T18:00"),
            FlightSegment("QF", "12", "SIN", "HND", "2025-06-08T20:00", "2025-06-09T03:00")
        ),
        price = Price("920.00", "AUD")
    ),
    Flight(
        id = "9",
        segments = listOf(
            FlightSegment("UA", "845", "LAX", "DEN", "2025-06-09T09:00", "2025-06-09T11:30"),
            FlightSegment("UA", "555", "DEN", "ORD", "2025-06-09T13:00", "2025-06-09T16:30"),
            FlightSegment("UA", "100", "ORD", "BOS", "2025-06-09T18:00", "2025-06-09T21:00")
        ),
        price = Price("650.00", "USD")
    ),
    Flight(
        id = "10",
        segments = listOf(
            FlightSegment("AZ", "87", "FCO", "GRU", "2025-06-10T22:00", "2025-06-11T05:30")
        ),
        price = Price("850.00", "USD")
    )
)