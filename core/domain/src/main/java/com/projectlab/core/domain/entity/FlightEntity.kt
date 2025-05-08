package com.projectlab.core.domain.entity

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.Timestamp

data class FlightEntity(
    val airline: String = "",
    val flightNumber: String = "",
    val flightClass: String = "",
    val departureAirport: Map<String, Any> = mapOf(
        "airportCodeRef" to "",
        "time" to Timestamp.now()
    ),
    val arrivalAirport: Map<String, Any> = mapOf(
        "airportCodeRef" to "",
        "time" to Timestamp.now()
    ),
    val passengerNumber: List<Map<String, Any>> = emptyList(),
    val price: Double = 0.0,
    val itineraryRef: DocumentReference? = null
)
