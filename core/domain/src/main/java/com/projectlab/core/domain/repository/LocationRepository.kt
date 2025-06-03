package com.projectlab.core.domain.repository

import com.projectlab.core.domain.model.Location


interface LocationRepository {
    fun hasLocationPermission(): Boolean
    suspend fun reverseGeocodeLocation(location: Location): String
    suspend fun getCoordinatesFromLocation(locationName: String): Location?
    suspend fun getCurrentLocation(): Location?

}