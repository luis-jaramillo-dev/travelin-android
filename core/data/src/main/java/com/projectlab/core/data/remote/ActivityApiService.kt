package com.projectlab.core.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ActivityApiService {

    @GET("v1/shopping/activities/{activityId}")
    suspend fun getActivityById(
        @Query("activityId") id: String
    ) : ActivityDetailResponse
}