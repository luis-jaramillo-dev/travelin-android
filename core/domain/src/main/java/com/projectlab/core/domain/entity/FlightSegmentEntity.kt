package com.projectlab.core.domain.entity

import com.projectlab.core.domain.model.EntityId
import java.time.Instant


data class FlightSegmentEntity (
    val id : String = "",
    val departureAirportCodeRef : EntityId? = null,
    val arrivalAirportCodeRef : EntityId? = null,
    val departureTime : Instant,
    val arrivalTime : Instant,
    val requiresPlaneChange : Boolean = false,
    val connectionInfo : Map<String, Any> = mapOf(
        "nextAirline" to "",
        "nextFlightNumber" to "",
        "connectionGate" to ""
    ),
    val userRef : EntityId? = null,
    val itineraryRef : EntityId? = null,
    val flightRef : EntityId? = null
)