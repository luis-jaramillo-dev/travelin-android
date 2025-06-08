package com.projectlab.core.domain.repository

import com.projectlab.core.domain.entity.ActivityEntity
import com.projectlab.core.domain.entity.FavoriteActivityEntity
import com.projectlab.core.domain.model.Activity
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.util.DataError
import com.projectlab.core.domain.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * ActivityRepository interface for activity-related operations.
 *
 * This interface defines the contract for activity-related data operations,
 * including creating an activity, retrieving activities by various criteria,
 * updating and deleting activities, and managing favorite activities.
 */

interface ActivityRepository {

    // DataBase
    suspend fun createActivity(
        itinId: String,
        activity: ActivityEntity
    ): kotlin.Result<EntityId>
    suspend fun getActivityById(
        itinId: String,
        activityId: String,
    ): kotlin.Result<ActivityEntity?>
    suspend fun getAllActivities(
        itinId: String,
    ): kotlin.Result<List<ActivityEntity>>
    suspend fun updateActivity(
        itinId: String,
        activity: ActivityEntity,
    ): kotlin.Result<Unit>
    suspend fun deleteActivity(
        itinId: String,
        activityId: String,
    ): kotlin.Result<Unit>

    suspend fun getAPIActivityById(id: String): Result<Activity, DataError.Network>

    fun queryFavoriteActivities(
        
        nameQuery: String? = null,
    ): Flow<FavoriteActivityEntity>

    fun getFavoriteActivities(userId: String): Flow<List<FavoriteActivityEntity>>

    suspend fun isFavoriteActivity(activityId: String): kotlin.Result<Boolean>

    suspend fun saveFavoriteActivity(
        
        activity: FavoriteActivityEntity,
    ): kotlin.Result<Unit>

    suspend fun removeFavoriteActivityById( activityId: String): kotlin.Result<Unit>

    suspend fun getActivitiesByCoordinates(
        latitude: Double,
        longitude: Double,
    ): Result<List<Activity>, DataError.Network>
}
