package com.projectlab.core.domain.use_cases.itineraries

import com.projectlab.core.domain.repository.ItineraryRepository
import javax.inject.Inject

class GetItinerariesByUserUseCase @Inject constructor(private val repository: ItineraryRepository) {
    suspend operator fun invoke(userId: String) = repository.getItineraryById(userId)
}