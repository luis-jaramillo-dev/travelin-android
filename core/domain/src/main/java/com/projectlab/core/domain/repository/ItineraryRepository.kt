package com.projectlab.core.domain.repository;

import com.projectlab.core.domain.entity.ItineraryEntity
import com.google.firebase.firestore.DocumentReference
import kotlin.Unit;

interface ItineraryRepository {
    suspend fun createItinerary(itinerary: ItineraryEntity): Result<String>
    suspend fun getItineraries(userRef: DocumentReference): Result<List<ItineraryEntity>>
}
