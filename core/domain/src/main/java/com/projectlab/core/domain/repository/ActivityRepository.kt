package com.projectlab.core.domain.repository

import com.projectlab.core.domain.entity.ActivityEntity
import com.projectlab.core.domain.model.Activity
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.util.DataError
import com.projectlab.core.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface ActivityRepository {
    suspend fun getAPIActivityById(id: String): Result<Activity, DataError.Network>
    suspend fun createActivity(activity: ActivityEntity): kotlin.Result<EntityId>
    suspend fun getActivityById(id: String): Flow<ActivityEntity?>
    suspend fun getActivitiesByCoordinates(
        latitude: Double,
        longitude: Double,
    ): Result<List<Activity>, DataError.Network>
}
