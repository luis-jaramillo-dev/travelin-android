package com.projectlab.core.domain.entity

import com.projectlab.core.domain.model.EntityId
import java.time.Instant

/**
 * FlightEntity represents a flight in the system.
 * @property id Unique identifier for the flight Segment.
 * @property departureAirportCodeRef Reference to the departure airport code.
 * @property arrivalAirportCodeRef Reference to the arrival airport code.
 * @property departureTime Departure time of the flight.
 * @property arrivalTime Arrival time of the flight.
 * @property requiresPlaneChange Indicates if a plane change is required (if it's a connection
 * or stopover).
 * @property connectionInfo Information about the connection, including next airline, flight number,
 * and connection gate.
 * @property userRef Reference to the user associated with the itinerary, that is associated with
 * the flight, that is associated with the flight segment.
 * @property itineraryRef Reference to the itinerary associated with the flight, that is associated
 * with the flight segment.
 * @property flightRef Reference to the flight associated with the flight segment.
 *
 * @author ricardoceadev
 *
 */


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