package com.projectlab.travelin_android.usecase

import jakarta.inject.Inject
import com.projectlab.travelin_android.model.CityLocation
import com.projectlab.travelin_android.repository.FlightRepository

class SearchCityLocationsUseCase @Inject constructor(
    private val repository: FlightRepository
) {
    suspend operator fun invoke(keyword: String): List<CityLocation> {
        return repository.searchCityLocations(keyword)
    }
}