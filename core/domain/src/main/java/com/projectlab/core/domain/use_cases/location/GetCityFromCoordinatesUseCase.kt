package com.projectlab.core.domain.use_cases.location

fun interface GetCityFromCoordinatesUseCase {
    suspend operator fun invoke(lat: Double, lng: Double): String
}