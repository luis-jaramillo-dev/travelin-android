package com.projectlab.core.domain.use_cases.hotels

import com.projectlab.core.domain.model.Hotel
import com.projectlab.core.domain.util.DataError
import com.projectlab.core.domain.util.Result

fun interface GetHotelsByCoordinatesUseCase {
    suspend operator fun invoke(
        latitude: Double,
        longitude: Double,
        amenities: List<String>,
        ratings: List<String>
    ): Result<List<Hotel>, DataError>
}