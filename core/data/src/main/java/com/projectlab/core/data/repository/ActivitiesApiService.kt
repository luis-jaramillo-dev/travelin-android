package com.projectlab.core.data.repository

import com.projectlab.core.data.remote.ActivitiesSearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ActivitiesApiService {

    @GET("v1/shopping/activities")
    suspend fun getActivitiesByLocation(
        @Header("Authorization") authorization: String,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("radius") radius: Int = 20
    ): ActivitiesSearchResponse
}