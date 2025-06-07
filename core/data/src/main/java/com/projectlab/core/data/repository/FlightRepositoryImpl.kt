package com.projectlab.core.data.repository

import com.projectlab.core.database.services.FirestoreFlight
import com.projectlab.core.database.services.FirestoreFlightImpl
import com.projectlab.core.domain.entity.FlightEntity
import com.projectlab.core.domain.entity.FlightSegmentEntity
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.repository.FlightRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * FlightRepositoryImpl is the implementation of the FlightRepository interface.
 * It performs operations on flights using services.
 *
 * @param firestoreFlight The FirestoreFlight service used for database operations.
 */

class FlightRepositoryImpl @Inject constructor (
    private val firestoreFlight: FirestoreFlight
) : FlightRepository {
    override suspend fun createFlight(itinId: String, flight: FlightEntity): Result<EntityId> {
        return firestoreFlight.createFlight(itinId, flight)
    }

    override suspend fun getFlightById(
        itinId: String,
        flightId: String
    ): Result<FlightEntity?> {
        return firestoreFlight.getFlightById(itinId, flightId)
    }

    override suspend fun getAllFlights(
        itinId: String
    ): Result<List<FlightEntity>> {
        return firestoreFlight.getAllFlightsForItinerary(itinId)
    }

    override suspend fun updateFlight(itinId: String, flight: FlightEntity): Result<Unit> {
        return firestoreFlight.updateFlight(itinId, flight)
    }

    override suspend fun deleteFlight(
        itinId: String,
        flightId: String
    ): Result<Unit> {
        return firestoreFlight.deleteFlight(itinId, flightId)
    }

    override suspend fun createFlightSegment(
        itinId: String,
        flightId: String,
        segment: FlightSegmentEntity
    ): Result<EntityId> {
        return firestoreFlight.createFlightSegment(itinId, flightId, segment)
    }

    override suspend fun getFlightSegmentById(
        itinId: String,
        flightId: String,
        segmentId: String
    ): Result<FlightSegmentEntity?> {
        return firestoreFlight.getFlightSegmentById(itinId, flightId, segmentId)
    }

    override suspend fun getAllFlightSegments(
        itinId: String,
        flightId: String
    ): Result<List<FlightSegmentEntity>> {
        return firestoreFlight.getAllFlightSegmentsForFlight(itinId, flightId)
    }

    override suspend fun updateFlightSegment(
        itinId: String,
        flightId: String,
        segment: FlightSegmentEntity
    ): Result<Unit> {
        return firestoreFlight.updateFlightSegment(itinId, flightId,segment)
    }

    override suspend fun deleteFlightSegment(
        itinId: String,
        flightId: String,
        segmentId: String
    ): Result<Unit> {
        return firestoreFlight.deleteFlightSegment(itinId, flightId, segmentId)
    }

}