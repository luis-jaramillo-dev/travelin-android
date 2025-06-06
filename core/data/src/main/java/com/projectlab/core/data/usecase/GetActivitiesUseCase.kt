package com.projectlab.core.data.usecase

import android.util.Log
import com.projectlab.core.data.mapper.toDomain
import com.projectlab.core.data.remote.ActivitiesApiService
import com.projectlab.core.domain.model.Activity
import com.projectlab.core.domain.repository.TokenProvider
import com.projectlab.core.domain.util.DataError
import com.projectlab.core.domain.util.Result
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * Use case for fetching activities based on latitude and longitude.
 *
 * @property api The API service to fetch activities.
 * @property tokenProvider The token provider for API authentication.
 */

open class GetActivitiesUseCase @Inject constructor(
    private val api: ActivitiesApiService,
    private val tokenProvider: TokenProvider
) {

    /**
     * Fetches activities based on the provided latitude and longitude.
     *
     * @param latitude The latitude of the location.
     * @param longitude The longitude of the location.
     * @return A Result containing a list of activities or an error.
     */

    open suspend operator fun invoke(
        latitude: Double,
        longitude: Double
    ): Result<List<Activity>, DataError.Network> {
        return try {
            val response = api.getActivitiesByLocation(latitude, longitude)
            val mappedActivities = response.data.map { it.toDomain() }
            Result.Success(mappedActivities)
        } catch (e: Exception) {
            Log.e("GetActivitiesUseCase", "Exception during API call", e)
            val networkError = when (e) {
                is UnknownHostException -> DataError.Network.NO_INTERNET
                is SocketTimeoutException -> DataError.Network.REQUEST_TIMEOUT
                is retrofit2.HttpException -> {
                    when (e.code()) {
                        401 -> DataError.Network.UNAUTHORIZED
                        500 -> DataError.Network.SERVER_ERROR
                        else -> DataError.Network.UNKNOWN
                    }
                }
                else -> DataError.Network.UNKNOWN
            }
            Result.Error(networkError)
        }
    }
}