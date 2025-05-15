package com.projectlab.core.domain.repository;

import com.projectlab.core.domain.entity.ItineraryEntity
import com.projectlab.core.domain.model.EntityId
import kotlinx.coroutines.flow.Flow
import kotlin.Unit;

/**
 * ItineraryRepository interface for itinerary-related operations.
 *
 * This interface defines the contract for itinerary-related data operations, including creating an
 * itinerary and retrieving an itinerary by its ID.
 *
 * @author ricardoceadev
 */

interface ItineraryRepository {
    suspend fun createItinerary(itinerary: ItineraryEntity): Result<EntityId>
    suspend fun getItinerariesById(id: String): Flow<ItineraryEntity?>
}
