package com.projectlab.booking.data.activities.remote

import com.projectlab.core.data.remote.ActivitiesSearchResponse
import com.projectlab.core.data.remote.ActivitiesApiService
import javax.inject.Inject


class ActivitiesRemoteDataSource @Inject constructor(
    private val apiService: ActivitiesApiService
) {
    suspend fun getActivities(
        latitude: Double,
        longitude: Double,
        radius: Int = 20
    ): ActivitiesSearchResponse {
        return apiService.getActivitiesByLocation(latitude, longitude, radius)
    }
}
