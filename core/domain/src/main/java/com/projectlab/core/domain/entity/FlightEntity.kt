package com.projectlab.core.domain.entity

import com.projectlab.core.domain.model.EntityId
import java.time.Instant

/**
 * FlightEntity represents a flight in the database system inside the app.
 * @property id Unique identifier for the flight.
 * @property airline Airline operating the flight.
 * @property flightNumber Flight number.
 * @property flightClass Class of the flight (e.g., economy, business).
 * @property departureAirport Details of the departure airport,
 * this include "airportCodeRef" for the airport reference and "time" for time departure.
 * @property arrivalAirport Details of the arrival airport,
 * this include "airportCodeRef" for the airport reference and "time" for time arrival.
 * @property passengerNumber Number of passengers (adults, kids, babies with sit, babies in arms).
 * @property price Price of the flight.
 *
 * @author ricardoceadev
 */


data class FlightEntity(
    val id : String = "",
    val airline: String = "",
    val flightNumber: String = "",
    val flightClass: String = "",
    val departureAirport: Map<String, Any> = mapOf(
        "airportCodeRef" to "",
        "time" to ""
    ),
    val arrivalAirport: Map<String, Any> = mapOf(
        "airportCodeRef" to "",
        "time" to ""
    ),
    val passengerNumber: Map<String, Any> = mapOf(
        "adultsNumber" to 0,
        "kidsNumber" to 0,
        "babiesWithSitNumber" to 0,
        "babiesInArmsNumber" to 0
    ),
    val price: Double = 0.0
)
