package com.projectlab.core.domain.entity

import com.projectlab.core.domain.model.EntityId
import java.time.Instant


data class FlightEntity(
    val id : EntityId? = null,
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
    val passengerNumber: List<Map<String, Any>> = emptyList(),
    val price: Double = 0.0,
    val itineraryRef: EntityId
)
