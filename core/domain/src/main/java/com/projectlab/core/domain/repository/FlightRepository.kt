package com.projectlab.core.domain.repository

import com.projectlab.core.domain.entity.FlightEntity
import com.projectlab.core.domain.model.EntityId

interface FlightRepository {
    suspend fun createFlight(flight: FlightEntity): Result<EntityId>
    suspend fun getFlightsById(id: String): FlightEntity?
}