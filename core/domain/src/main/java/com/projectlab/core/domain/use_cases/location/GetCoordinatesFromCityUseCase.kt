package com.projectlab.core.domain.use_cases.location

fun interface GetCoordinatesFromCityUseCase {
    suspend operator fun invoke(city: String): Pair<Double, Double>?
}