package com.projectlab.core.data.repository

import android.util.Log
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
        if (token.isEmpty()) {
            throw Exception("Access token is missing")
        }
        val response = apiService.getActivitiesByLocation(
            latitude = lat,
            longitude = lon
        )
        Log.d("ActivitiesRepository", "API Response: ${response.data}")
        return response.data.toDomainList()
    }
}