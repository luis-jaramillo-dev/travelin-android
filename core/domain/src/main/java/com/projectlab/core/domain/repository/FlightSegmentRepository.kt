package com.projectlab.core.domain.repository

import com.projectlab.core.domain.entity.FlightSegmentEntity
import com.projectlab.core.domain.model.EntityId

interface FlightSegmentRepository {
    suspend fun createFlightSegment(flightSegment: FlightSegmentEntity): Result<EntityId>
    suspend fun getFlightSegmentById(id: String): FlightSegmentEntity?
}