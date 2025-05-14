package com.projectlab.core.domain.repository;

import com.projectlab.core.domain.entity.ItineraryEntity
import com.projectlab.core.domain.model.EntityId
import kotlinx.coroutines.flow.Flow
import kotlin.Unit;

interface ItineraryRepository {
    suspend fun createItinerary(itinerary: ItineraryEntity): Result<EntityId>
    suspend fun getItinerariesById(id: String): Flow<ItineraryEntity?>
}
