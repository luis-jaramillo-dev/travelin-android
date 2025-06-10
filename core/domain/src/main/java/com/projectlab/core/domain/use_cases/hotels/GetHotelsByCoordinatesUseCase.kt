package com.projectlab.core.domain.use_cases.hotels

import com.projectlab.core.domain.repository.HotelsRepository
import javax.inject.Inject

class GetHotelsByCoordinatesUseCase @Inject constructor(private val repository: HotelsRepository){

    suspend operator fun invoke(
        latitude: Double,
        longitude: Double,
        amenities: List<String> = emptyList(),
        ratings: List<String> = emptyList()
    ) = repository.getHotelsByCoordinates(latitude, longitude, amenities.toString(), ratings.toString())
}