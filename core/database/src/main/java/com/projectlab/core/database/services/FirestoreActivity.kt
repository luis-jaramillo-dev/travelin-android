package com.projectlab.core.database.services

import com.projectlab.core.domain.entity.ActivityEntity
import com.projectlab.core.domain.model.EntityId
import kotlinx.coroutines.flow.Flow

interface FirestoreActivity {

    suspend fun createActivity(
        itinId: String,
        activity: ActivityEntity
    ): Result<EntityId>
    suspend fun getActivityById(
        itinId: String,
        activityId: String
    ): Result<ActivityEntity?>
    suspend fun getAllActivitiesForItinerary(
         itinId: String
    ): Result<List<ActivityEntity>>
    suspend fun updateActivity(
        itinId: String,
        activity: ActivityEntity
    ): Result<Unit>
    suspend fun deleteActivity(
         itinId: String, activityId: String
    ): Result<Unit>
}