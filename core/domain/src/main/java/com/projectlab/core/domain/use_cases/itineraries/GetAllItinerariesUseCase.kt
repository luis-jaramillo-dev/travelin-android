package com.projectlab.core.domain.use_cases.itineraries

import com.projectlab.core.domain.entity.ItineraryEntity

fun interface GetAllItinerariesUseCase  {
    suspend operator fun invoke(userId : String): Result<List<ItineraryEntity>>
}