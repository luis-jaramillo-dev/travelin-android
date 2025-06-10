package com.projectlab.core.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ActivityApiService {
    @GET("v1/shopping/activities/{activityId}")
    suspend fun getActivityById(
        @Path("activityId") id: String,
    ): ActivityDetailResponse

    @GET("v1/shopping/activities")
    suspend fun getActivitiesByLocation(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("radius") radius: Int = 5,
    ): ActivitiesSearchResponse
}
