package com.projectlab.core.domain.repository

import com.projectlab.core.domain.entity.FlightEntity
import com.projectlab.core.domain.model.EntityId
import kotlinx.coroutines.flow.Flow

/**
 * FlightRepository interface for flight-related operations.
 *
 * This interface defines the contract for flight-related data operations, including creating a flight
 * and retrieving a flight by its ID.
 *
 * @author ricardoceadev
 */

interface FlightRepository {
    suspend fun createFlight(flight: FlightEntity): Result<EntityId>
    suspend fun getFlightById(userId: String, itinId: String, flightId: String): Flow<FlightEntity?>
    suspend fun getAllFlightsForItinerary(userId: String, itinId: String): Flow<List<FlightEntity>>
    suspend fun updateFlight(flight: FlightEntity): Result<Unit>
    suspend fun deleteFlight(userId: String, itinId: String, flightId: String): Result<Unit>
}