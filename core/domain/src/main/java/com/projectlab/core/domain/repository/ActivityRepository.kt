package com.projectlab.core.domain.repository

import com.projectlab.core.domain.entity.ActivityEntity
import com.projectlab.core.domain.model.EntityId

interface ActivityRepository {
    suspend fun createActivity(activity: ActivityEntity): Result<EntityId>
    suspend fun getActivityById(id: String): ActivityEntity?
}