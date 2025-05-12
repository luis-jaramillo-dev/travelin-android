package repository

import android.util.Log
import api.FlightApiService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import jakarta.inject.Inject
import mapper.toDomain
import model.CityLocation
import model.Flight

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
        origin: String,
        destination: String,
        date: String
    ): List<Flight>{
        val response = api.getFlightOffers(origin,destination,date, adults = 1,max =5)
        val flights = response.data.map{it.toDomain()}
        Log.d("response", Gson().toJson(response))

        //Guardar en FireStore
        flights.forEach{
            firestore.collection("flights").document(it.id).set(it)
        }
        return flights
    }
}