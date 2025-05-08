package com.projectlab.core.domain.entity

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.Timestamp

data class FlightSegmentEntity (
    val detartureAirportCodeRef : DocumentReference? = null,
    val arrivalAirportCodeRef : DocumentReference? = null,
    val departureTime : Timestamp = Timestamp.now(),
    val arrivalTime : Timestamp = Timestamp.now(),
    val requiresPlaneChange : Boolean = false,
    val connectionInfo : Map<String, Any> = mapOf(
        "nextAirline" to "",
        "nextFlightNumber" to "",
        "connectionGate" to ""
    ),
    val flightRef : DocumentReference? = null
)