package com.projectlab.core.domain.use_cases.itineraries

import com.projectlab.core.domain.entity.ItineraryEntity
import com.projectlab.core.domain.repository.ItineraryRepository
import javax.inject.Inject

class CreateItineraryUseCase @Inject constructor(private val repository: ItineraryRepository) {
    suspend operator fun invoke(itinerary: ItineraryEntity) = repository.createItinerary(itinerary)
}