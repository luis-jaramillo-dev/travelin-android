package com.projectlab.core.domain.use_cases.itineraries

import com.projectlab.core.domain.entity.ActivityEntity

interface AddActivityToItineraryUseCase {
    suspend operator fun invoke(
        userId: String,
        activity : ActivityEntity
    ): Result<Unit>
}