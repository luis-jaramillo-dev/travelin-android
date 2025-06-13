package com.projectlab.core.domain.use_cases.itineraries

import com.projectlab.core.domain.entity.HotelEntity
import com.projectlab.core.domain.repository.HotelsRepository
import javax.inject.Inject

class AddHotelToItineraryUseCase @Inject constructor(private val repository: HotelsRepository) {
    suspend operator fun invoke(
        itinId: String,
        hotel: HotelEntity
    ) = repository.createHotel(itinId, hotel)
}