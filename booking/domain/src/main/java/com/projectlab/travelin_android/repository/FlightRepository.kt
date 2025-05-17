package com.projectlab.travelin_android.repository
import com.projectlab.travelin_android.model.CityLocation
import com.projectlab.travelin_android.model.Flight
import com.projectlab.travelin_android.model.FlightQueryParams

interface FlightRepository{

    suspend fun searchCityLocations(keyword: String): List<CityLocation>

    suspend fun getFlights(
        params: FlightQueryParams
    ):List<Flight>
}