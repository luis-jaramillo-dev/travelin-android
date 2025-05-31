package com.projectlab.core.data.usecase

import com.projectlab.core.data.utils.LocationUtils
import com.projectlab.core.domain.use_cases.location.GetCoordinatesFromCityUseCase
import javax.inject.Inject

class GetCoordinatesFromCityUseCaseImpl @Inject constructor(
    private val locationUtils: LocationUtils
) : GetCoordinatesFromCityUseCase{
    override suspend fun invoke(city: String): Pair<Double, Double>? {
        return locationUtils.getCoordinatesFromLocation(city)?.let {
            it.latitude to it.longitude
        }
    }
}