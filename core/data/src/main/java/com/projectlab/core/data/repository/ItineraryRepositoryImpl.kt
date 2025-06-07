package com.projectlab.core.data.repository

import com.projectlab.core.database.services.FirestoreItinerary
import com.projectlab.core.domain.entity.ItineraryEntity
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.repository.ItineraryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * ItineraryRepositoryImpl is the implementation of the ItineraryRepository interface.
 * It performs operations on itineraries using services.
 *
 * @param firestoreItinerary The FirestoreItinerary service used for database operations.
 */

class ItineraryRepositoryImpl  @Inject constructor(
    private val firestoreItinerary: FirestoreItinerary
) : ItineraryRepository {
    override suspend fun createItinerary(itinerary: ItineraryEntity): Result<EntityId> {
        return firestoreItinerary.createItinerary(itinerary)
    }

    override suspend fun getItineraryById(
        itineraryId: String
    ): Result<ItineraryEntity?> {
        return firestoreItinerary.getItinerariesById(itineraryId)
    }

    override suspend fun getAllItineraries(userId: String): Result<List<ItineraryEntity>> {
        return firestoreItinerary.getAllItinerariesForUser(userId)
    }

    override suspend fun updateItinerary(itinerary: ItineraryEntity): Result<Unit> {
        return firestoreItinerary.updateItinerary(itinerary)
    }

    override suspend fun deleteItinerary(
        itineraryId: String
    ): Result<Unit> {
        return firestoreItinerary.deleteItinerary(itineraryId)
    }
}