package com.projectlab.travelin_android.repository
import com.projectlab.travelin_android.model.CityLocation
import com.projectlab.travelin_android.model.Flight
interface FlightRepository{

    suspend fun searchCityLocations(keyword: String): List<CityLocation>

    suspend fun getFlights(
        origin: String,
        destination: String,
        date: String
    ):List<Flight>
}