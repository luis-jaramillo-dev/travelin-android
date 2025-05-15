package com.projectlab.core.domain.repository

import com.projectlab.core.domain.model.Activity
import com.projectlab.core.domain.util.DataError
import com.projectlab.core.domain.util.Result


interface ActivitiesRepository {
    suspend fun getActivitiesByCoordinates(lat: Double, lon: Double): Result<List<Activity>, DataError.Network>
}