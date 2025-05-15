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
    suspend fun getFlightsById(id: String): Flow<FlightEntity?>
}