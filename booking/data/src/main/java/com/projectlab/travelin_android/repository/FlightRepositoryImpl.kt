package com.projectlab.travelin_android.repository

import android.util.Log
import com.projectlab.travelin_android.api.FlightApiService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import jakarta.inject.Inject
import com.projectlab.travelin_android.mapper.toDomain
import com.projectlab.travelin_android.model.CityLocation
import com.projectlab.travelin_android.model.Flight
import com.projectlab.travelin_android.model.FlightQueryParams

class FlightRepositoryImpl @Inject constructor(
    private val api: FlightApiService,
    private val firestore: FirebaseFirestore
): FlightRepository {
    override suspend fun searchCityLocations(keyword: String): List<CityLocation> {
        return api.getLocations(keyword).data.map {
            CityLocation(
                city = it.name,
                iataCode = it.iataCode
            )
        }
    }
    override suspend fun getFlights(
        params:FlightQueryParams
    ): List<Flight>{
        val response = api.getFlightOffers(
            origin = params.originLocationCode,
            destination = params.destinationLocationCode,
            departureDate = params.departureDate,
            returnDate = params.returnDate,
            adults = params.adults,
            children = params.children,
            infants = params.infants,
            travelClass = params.travelClass?.name,
            nonStop = params.nonStop,
            maxPrice = params.maxPrice,
            max = params.max
        )
        val flights = response.data.map{it.toDomain()}
        Log.d("response", Gson().toJson(response))

        //Guardar en FireStore
        flights.forEach{
            firestore.collection("flights").document(it.id).set(it)
        }
        return flights
    }
}