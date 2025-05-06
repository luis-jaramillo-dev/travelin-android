package com.projectlab.core.data.repository

import com.projectlab.core.domain.repository.ActivitiesRepository
import javax.inject.Inject
import com.projectlab.core.domain.model.Activity
import com.projectlab.core.data.mapper.toDomainList
import com.projectlab.core.domain.repository.TokenProvider

class ActivitiesRepositoryImpl @Inject constructor(
    private val apiService: ActivitiesApiService,
    private val tokenProvider: TokenProvider
) : ActivitiesRepository {

    override suspend fun getActivitiesByCoordinates(lat: Double, lon: Double): List<Activity> {
        val token = tokenProvider.getAccessToken()
        val response = apiService.getActivitiesByLocation(
            authorization = "Bearer $token",
            latitude = lat,
            longitude = lon
        )
        return response.data.toDomainList()
    }
}