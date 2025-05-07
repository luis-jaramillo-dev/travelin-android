package usecase

import jakarta.inject.Inject
import model.Flight
import repository.FlightRepository

class GetFlightsUseCase @Inject constructor (
    private val repository: FlightRepository
) {
    suspend operator fun invoke(
        origin: String,
        destination: String,
        date: String
    ) : List<Flight>{
        return repository.getFlights(origin, destination, date)
    }
}