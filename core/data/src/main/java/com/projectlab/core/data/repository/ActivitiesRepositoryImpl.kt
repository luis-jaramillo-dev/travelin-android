package com.projectlab.core.data.repository

import com.projectlab.core.domain.repository.ActivitiesRepository
import javax.inject.Inject
import com.projectlab.core.domain.model.Activity
import com.projectlab.core.data.mapper.toDomainList

class ActivitiesRepositoryImpl @Inject constructor(
    private val apiService: ActivitiesApiService
) : ActivitiesRepository {

    override suspend fun getActivitiesByCoordinates(lat: Double, lon: Double): List<Activity> {
        val response = apiService.getActivitiesByLocation(lat, lon)
        return response.data.toDomainList()
    }
}