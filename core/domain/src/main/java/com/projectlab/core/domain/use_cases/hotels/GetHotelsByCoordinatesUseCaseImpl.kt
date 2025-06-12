package com.projectlab.core.domain.use_cases.hotels

import com.projectlab.core.domain.repository.HotelsRepository
import javax.inject.Inject

class GetHotelsByCoordinatesUseCaseImpl @Inject constructor(
    private val repository: HotelsRepository
) : GetHotelsByCoordinatesUseCase {

    override suspend operator fun invoke(
        latitude: Double,
        longitude: Double,
        amenities: List<String>,
        ratings: List<String>
    ) = repository.getHotelsByCoordinates(
        latitude,
        longitude,
        amenities.toString(),
        ratings.toString()
    )
}