package com.projectlab.core.domain.repository;

import com.projectlab.core.domain.entity.ItineraryEntity
import kotlin.Unit;

interface ItineraryRepository {
    suspend fun createItinerary(itinerary: ItineraryEntity): Result<String>
    suspend fun getItinerariesById(id: String): ItineraryEntity?
}
