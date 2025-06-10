package com.projectlab.core.domain.use_cases.activities

import com.projectlab.core.domain.model.Activity
import com.projectlab.core.domain.util.DataError
import com.projectlab.core.domain.util.Result

fun interface GetActivitiesUseCase {
    suspend operator fun invoke(
        latitude: Double,
        longitude: Double
    ): Result<List<Activity>, DataError.Network>

}