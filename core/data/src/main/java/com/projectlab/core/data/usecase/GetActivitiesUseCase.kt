package com.projectlab.core.data.usecase

import com.projectlab.core.data.remote.ActivitiesSearchResponse
import com.projectlab.core.data.repository.ActivitiesApiService
import javax.inject.Inject

class GetActivitiesUseCase @Inject constructor(
    private val apiService: ActivitiesApiService
) {
    suspend operator fun invoke(latitude: Double, longitude: Double): ActivitiesSearchResponse {
        return apiService.getActivitiesByLocation(latitude, longitude)
    }
}