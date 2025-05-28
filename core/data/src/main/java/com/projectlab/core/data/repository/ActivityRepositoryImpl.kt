package com.projectlab.core.data.repository

import com.projectlab.core.data.mapper.toDomain
import com.projectlab.core.data.remote.ActivityApiService
import com.projectlab.core.domain.model.Activity
import com.projectlab.core.domain.repository.ActivityRepository
import com.projectlab.core.domain.repository.TokenProvider
import com.projectlab.core.domain.util.DataError
import com.projectlab.core.domain.util.Result
import javax.inject.Inject

class ActivityRepositoryImpl @Inject constructor(
    private val activityApiService: ActivityApiService,
) : ActivityRepository {
    override suspend fun getActivityById(
        id: String
    ): Result<Activity, DataError.Network> {
        return try {
            val response = activityApiService.getActivityById(id)
            Result.Success(response.data.toDomain())
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(DataError.Network.UNKNOWN)
        }
    }
}