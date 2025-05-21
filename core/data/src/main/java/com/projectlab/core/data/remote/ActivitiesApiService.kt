package com.projectlab.core.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * ActivitiesApiService is an interface that defines the API endpoints for retrieving activities
 * based on location. It includes a method to get activities by latitude and longitude.
 */

interface ActivitiesApiService {

    @GET("v1/shopping/activities")
    suspend fun getActivitiesByLocation(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("radius") radius: Int = 5
    ): ActivitiesSearchResponse
}