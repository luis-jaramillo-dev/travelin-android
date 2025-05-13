package com.projectlab.core.data.repository

import android.util.Log
import com.projectlab.core.data.mapper.toDomain
import com.projectlab.core.domain.repository.ActivitiesRepository
import javax.inject.Inject
import com.projectlab.core.domain.model.Activity
import com.projectlab.core.data.mapper.toDomainList
import com.projectlab.core.domain.repository.TokenProvider
import com.projectlab.core.domain.util.DataError
import com.projectlab.core.domain.util.Result

class ActivitiesRepositoryImpl @Inject constructor(
    private val apiService: ActivitiesApiService,
    private val tokenProvider: TokenProvider
) : ActivitiesRepository {

    override suspend fun getActivitiesByCoordinates(
        lat: Double,
        lon: Double
    ): Result<List<Activity>, DataError.Network> {
        val token = tokenProvider.getAccessToken()
        Log.d("ActivitiesRepository", "Obtained token: $token")
        if (token.isEmpty()) {
            throw Exception("Access token is missing")
        }
        return try {
            val response = apiService.getActivitiesByLocation(latitude = lat, longitude = lon)
            Result.Success(response.data.map { it.toDomain() })
        } catch (e: Exception) {
            Log.e("ActivitiesRepository", "Error fetching activities", e)
            Result.Error(DataError.Network.UNKNOWN)
        }
    }
}
