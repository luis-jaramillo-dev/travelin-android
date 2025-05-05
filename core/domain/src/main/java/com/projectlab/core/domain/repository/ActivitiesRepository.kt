package com.projectlab.core.domain.repository

import com.projectlab.core.domain.model.Activity


interface ActivitiesRepository {
    suspend fun getActivitiesByCoordinates(lat: Double, lon: Double): List<Activity>
}