package com.projectlab.core.domain.repository

import com.projectlab.core.domain.model.Activity
import com.projectlab.core.domain.util.DataError
import com.projectlab.core.domain.util.Result

interface ActivityRepository {
    suspend fun getActivityById(id: String): Result<Activity, DataError.Network>
}