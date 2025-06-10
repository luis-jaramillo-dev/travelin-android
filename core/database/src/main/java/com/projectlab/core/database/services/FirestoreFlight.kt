package com.projectlab.core.database.services

import com.projectlab.core.domain.entity.FlightEntity
import com.projectlab.core.domain.entity.FlightSegmentEntity
import com.projectlab.core.domain.model.EntityId
import kotlinx.coroutines.flow.Flow

/**
 * FirestoreFlight interface for flight-related operations.
 *
 * This interface defines the contract for flight-related data operations, including creating a flight
 * and retrieving a flight by its ID.
 *
 * @author ricardoceadev
 */

interface FirestoreFlight {
    // Flight operations
    suspend fun createFlight(
        itinId: String,
        flight: FlightEntity
    ): Result<EntityId>
    suspend fun getFlightById(
        itinId: String,
        flightId: String
    ): Result<FlightEntity?>
    suspend fun getAllFlightsForItinerary(
        itinId: String
    ): Result<List<FlightEntity>>
    suspend fun updateFlight(
        itinId: String,
        flight: FlightEntity
    ): Result<Unit>
    suspend fun deleteFlight( itinId: String, flightId: String): Result<Unit>

    // Flight segment operations
    suspend fun createFlightSegment(
        itinId: String,
        flightId: String,
        flightSegment: FlightSegmentEntity
    ): Result<EntityId>
    suspend fun getFlightSegmentById(
        itinId: String,
        flightId: String,
        segmentId: String
    ): Result<FlightSegmentEntity?>
    suspend fun getAllFlightSegmentsForFlight(
        itinId: String,
        flightId: String
    ): Result<List<FlightSegmentEntity>>
    suspend fun updateFlightSegment(
        itinId: String,
        flightId: String,
        flightSegment: FlightSegmentEntity
    ): Result<Unit>
    suspend fun deleteFlightSegment(
        itinId: String,
        flightId: String,
        segmentId: String
    ): Result<Unit>
}