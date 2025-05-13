package com.projectlab.core.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ActivitiesApiService {

    @GET("v1/shopping/activities")
    suspend fun getActivitiesByLocation(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("radius") radius: Int = 5
    ): ActivitiesSearchResponse
}