package usecase

import jakarta.inject.Inject
import model.CityLocation
import repository.FlightRepository

class SearchCityLocationsUseCase @Inject constructor(
    private val repository: FlightRepository
) {
    suspend operator fun invoke(keyword: String): List<CityLocation> {
        return repository.searchCityLocations(keyword)
    }
}