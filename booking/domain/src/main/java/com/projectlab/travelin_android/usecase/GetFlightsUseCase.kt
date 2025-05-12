package com.projectlab.travelin_android.usecase

import jakarta.inject.Inject
import com.projectlab.travelin_android.model.Flight
import com.projectlab.travelin_android.repository.FlightRepository

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