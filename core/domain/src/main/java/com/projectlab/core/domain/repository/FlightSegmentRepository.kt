package com.projectlab.core.domain.repository

import com.projectlab.core.domain.entity.FlightSegmentEntity
import com.projectlab.core.domain.model.EntityId
import kotlinx.coroutines.flow.Flow

/**
 * FlightSegmentRepository interface for flight segment-related operations.
 *
 * This interface defines the contract for flight segment-related data operations, including creating a
 * flight segment and retrieving a flight segment by its ID.
 *
 * @author ricardoceadev
 */

interface FlightSegmentRepository {
    suspend fun createFlightSegment(flightSegment: FlightSegmentEntity): Result<EntityId>
    suspend fun getFlightSegmentById(
        userId: String,
        itinId: String,
        flightId: String,
        segmentId: String
    ): Flow<FlightSegmentEntity?>
    suspend fun getAllFlightSegmentsForFlight(
        userId: String,
        itinId: String,
        flightId: String
    ): Flow<List<FlightSegmentEntity>>
    suspend fun updateFlightSegment(flightSegment: FlightSegmentEntity): Result<Unit>
    suspend fun deleteFlightSegment(
        userId: String,
        itinId: String,
        flightId: String,
        segmentId: String
    ): Result<Unit>

}