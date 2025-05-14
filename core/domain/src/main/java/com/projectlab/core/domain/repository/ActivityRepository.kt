package com.projectlab.core.domain.repository

import com.projectlab.core.domain.entity.ActivityEntity
import com.projectlab.core.domain.model.EntityId
import kotlinx.coroutines.flow.Flow

interface ActivityRepository {
    suspend fun createActivity(activity: ActivityEntity): Result<EntityId>
    suspend fun getActivityById(id: String): Flow<ActivityEntity?>
}