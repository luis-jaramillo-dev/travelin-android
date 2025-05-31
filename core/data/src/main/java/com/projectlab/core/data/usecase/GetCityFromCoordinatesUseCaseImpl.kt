package com.projectlab.core.data.usecase

import com.projectlab.core.data.utils.LocationUtils
import com.projectlab.core.domain.model.Location
import com.projectlab.core.domain.use_cases.location.GetCityFromCoordinatesUseCase
import javax.inject.Inject

class GetCityFromCoordinatesUseCaseImpl @Inject constructor(
    private val locationUtils: LocationUtils
) : GetCityFromCoordinatesUseCase {
    override suspend fun invoke(lat: Double, lng: Double): String {
        return locationUtils.reverseGeocodeLocation(Location(lat, lng))
    }
}