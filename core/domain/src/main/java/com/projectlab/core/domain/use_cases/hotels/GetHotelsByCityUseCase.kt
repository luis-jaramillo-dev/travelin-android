package com.projectlab.core.domain.use_cases.hotels

import com.projectlab.core.domain.repository.HotelsRepository
import javax.inject.Inject


class GetHotelsByCityUseCase @Inject constructor(private val repository: HotelsRepository) {

    suspend operator fun invoke(
        cityCode: String,
        amenities: List<String> = emptyList(),
        ratings: List<String> = emptyList()
    ) = repository.getHotelsByCity(cityCode, amenities.toString(), ratings.toString())

}