package com.projectlab.core.domain.entity

import com.projectlab.core.domain.model.EntityId
import java.time.Instant


data class FlightSegmentEntity (
    val id : EntityId? = null,
    val detartureAirportCodeRef : EntityId,
    val arrivalAirportCodeRef : EntityId,
    val departureTime : Instant,
    val arrivalTime : Instant,
    val requiresPlaneChange : Boolean = false,
    val connectionInfo : Map<String, Any> = mapOf(
        "nextAirline" to "",
        "nextFlightNumber" to "",
        "connectionGate" to ""
    ),
    val flightRef : EntityId
)