package com.projectlab.core.domain.repository

import com.projectlab.core.domain.entity.ActivityEntity
import com.projectlab.core.domain.model.EntityId
import kotlinx.coroutines.flow.Flow

/**
 * ActivityRepository interface for activity-related operations.
 *
 * This interface defines the contract for activity-related data operations, including creating an
 * activity and retrieving an activity by its ID.
 *
 * @author ricardoceadev
 */

interface FirestoreActivityRepository {
    suspend fun createActivity(activity: ActivityEntity): Result<EntityId>
    suspend fun getActivityById(userId: String, itinId: String, activityId: String): Flow<ActivityEntity?>
    suspend fun getAllActivitiesForItinerary(userId: String, itinId: String): Flow<List<ActivityEntity>>
    suspend fun updateActivity(activity: ActivityEntity): Result<Unit>
    suspend fun deleteActivity(userId: String, itinId: String, activityId: String): Result<Unit>
}